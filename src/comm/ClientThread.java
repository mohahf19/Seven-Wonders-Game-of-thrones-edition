package comm;

import backend.app.Main;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ClientThread implements Runnable {

    public GameHost host;

    public Socket socket;
    public BufferedReader in;
    public PrintWriter out;


    private int id;
    private Thread thread;
    private Gson gson;


    private volatile boolean isRunning = true;


    public ClientThread( int id, Socket socket, GameHost host) {
        try {
            gson = new Gson();

            this.id = id;
            this.socket = socket;
            this.host = host;

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            thread = new Thread(this);
            thread.start();

        } catch (IOException e) {
            System.out.println("IOException");
            System.out.println(e);
        }
    }

    public void sendRequestToClient( JsonObject outOb){
        out.println( gson.toJson(outOb));
    }

    public void run() {
        String input = "";
        try {
            while (isRunning) {
                if (!in.ready())
                    continue;

                if( Main.state == 1){
                    input = in.readLine();
                    JsonObject ob =  gson.fromJson( input, JsonObject.class);
//                    System.out.println("data received on server: " + input);
                    host.receiveRequest( id, ob);
                }
            }

            out.close();
            in.close();
            socket.close();
        } catch (Exception e) {
            System.out.print("Exception in thread: ");
            System.out.println( input);
            System.out.print(" | line: " + e.getLocalizedMessage());
            System.out.println(e.toString());
        }
    }
}

package comm;

import backend.app.Main;

import java.io.*;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


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
            System.out.println(e);
        }
    }

    public void sendRequestToClient( JsonObject outOb){
        out.println( gson.toJson(outOb));
    }

    public void run() {
        try {
            while (isRunning) {
                if (!in.ready())
                    continue;

                if( Main.state == 1){
                    String input = in.readLine();
                    JsonObject ob =  gson.fromJson( input, JsonObject.class);
                    System.out.println("data received on server: " + input);

                    host.receiveRequest( id, ob);
                }
            }

            out.close();
            in.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

package backend.models;

import backend.app.Main;
import backend.controllers.ConnectionController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ClientThread implements Runnable {


    public Socket socket;
    public BufferedReader in;
    public PrintWriter out;

    private int id;

    private Thread thread;


    private volatile boolean isRunning = true;


    public ClientThread( int id, Socket socket) {
        try {
            this.id = id;
            this.socket = socket;

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            thread = new Thread(this);
            thread.start();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void run() {
        try {
            while (isRunning) {
                if (!in.ready())
                    continue;

                if( ConnectionController.state == 0){
                    String input = in.readLine();
                    JSONParser parser = new JSONParser();
                    JSONObject ob = (JSONObject) parser.parse( input);

                    int op = Integer.parseInt( (String) ob.get( "op_code"));
                    switch ( op) {
                        case 0: {
                            System.out.println( "Invalid opcode");System.out.println( "Invalid opcode");
                            break;
                        }
                        case 1: {
                            System.out.println( "opcode: 1");
                            break;
                        }
                        default:
                            System.out.println( "opcode: 2");
                    };
//                    if( input.charAt( 0) == '*'){
//                        System.out.println("data received on server: " + input);
//                        out.println( "*" + id);
//                    } else {
//                        Main.game.conn.updateHouses();
//                    }
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

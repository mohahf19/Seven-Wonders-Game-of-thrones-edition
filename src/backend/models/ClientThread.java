package backend.models;

import backend.app.Main;
import backend.controllers.ConnectionController;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class ClientThread implements Runnable {


    public Socket socket;
    public BufferedReader in;
    public PrintWriter out;

    private int id;

    private Thread thread;

    private Gson gson;


    private volatile boolean isRunning = true;


    public ClientThread( int id, Socket socket) {
        try {
            gson = new Gson();


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
                    JsonObject ob =  gson.fromJson( input, JsonObject.class);
                    System.out.println("data received on server: " + input);


                    int op = Integer.parseInt( ob.get( "op_code").getAsString());
                    switch ( op) {
                        case 0: { //client identified
                            Player player = Main.game.conn.server.players.get( id);
                            JsonObject outOb = new JsonObject();
                            outOb.addProperty( "op_code", 0);
                            outOb.addProperty( "player", gson.toJson( player, Player.class));

                            out.println( gson.toJson(outOb));
                            break;
                        }
                        case 1: { //update houses on all clients
                            Main.game.conn.updateHouses();
                            break;
                        } case 2: { //start game request
                            Main.game.conn.server.isReceiving = false;

                            JsonObject outOb = new JsonObject();
                            outOb.addProperty( "op_code", 3);
                            out.println( gson.toJson(outOb));
                            break;
                        }
                        default:
                            System.out.println( "Invalid opcode");
                    }
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

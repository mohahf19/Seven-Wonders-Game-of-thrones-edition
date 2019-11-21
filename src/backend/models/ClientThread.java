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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


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
                    JSONParser parser = new JSONParser();
                    JSONObject ob = (JSONObject) parser.parse( input);
                    System.out.println("data received on server: " + ob.toJSONString());


                    int op = Integer.parseInt( "" + ob.get( "op_code"));
                    switch ( op) {
                        case 0: {
                            Player player = Main.game.conn.server.players.get( id);
                            JsonObject outOb = new JsonObject();
                            outOb.addProperty( "op_code", 0);
                            outOb.addProperty( "player", gson.toJson( player, Player.class));

                            out.println( gson.toJson(outOb));
                            break;
                        }
                        case 1: {
                            Main.game.conn.updateHouses();
                            break;
                        } case 2: {
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

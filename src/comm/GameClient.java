package comm;

import backend.app.constants;
import backend.controllers.WaitScreenController;
import backend.models.House;
import backend.models.Player;
import backend.models.Scoreboard;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class GameClient {
    public GameEngine engine;

    public String serverAddress;

    public Socket socket;
    public BufferedReader in;
    public PrintWriter out;

    public int id = -1;

    private Gson gson;

    public GameClient( String server, GameEngine engine) {
        this.engine = engine;
        serverAddress = server;
        gson = new Gson();
    }
    public void startClient(){
        initClient();
        connectClient();
    }

    public void initClient() {
        try {
            if (serverAddress == null)
                System.exit(1);

            serverAddress = serverAddress.trim();
            if (serverAddress.length() == 0)
            {
                System.out.println("Server IP Address or Name can't be blank.");
                initClient();
                return;
            }
            System.out.println("Connecting to server:"
                    + serverAddress);

            // create socket
            InetAddress inetAddress = InetAddress.getByName(serverAddress);
            if (!inetAddress.isReachable(15000))
            {
                System.out
                        .println("Unable to connect to server.");
                System.exit(1);
            }

            initPortNo();
        } catch (SocketException e) {
            System.out.println("Socket Exception: " + e);
            return;
        } catch (Exception e) {
            System.out.println( "Exception: " + e);
            return;
        }
    }

    public void initPortNo() {
        try {

            String portNo = constants.PORT_NO;

            socket = new Socket(serverAddress, Integer.parseInt( portNo));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            System.out.println("IO Exception:\n" + e);
            return;
        }
    }

    public void acknowledgeRequest(){
        JsonObject outOb = new JsonObject();
        outOb.addProperty( "op_code", -1);
        outOb.addProperty( "player_id", id);
        out.println( gson.toJson( outOb));
    }


    public void connectClient() {
        try {

            //initiate relation with server
            JsonObject ob = new JsonObject();
            ob.addProperty("op_code", 0);
            out.println( gson.toJson( ob));

            while (true) {
                String response = in.readLine();
                System.out.println("data received on client: " + response);

                JsonObject res = gson.fromJson( response, JsonObject.class);

                int op = Integer.parseInt( res.get( "op_code").getAsString());
                switch ( op) {
                    case -1: { //error from server
                        String error = res.get("error").getAsString();
                        JOptionPane.showMessageDialog(null, "" + error, "Error", JOptionPane.INFORMATION_MESSAGE);

                        acknowledgeRequest();
                        break;
                    }
                    case 0: { //received player details
                        id = Integer.parseInt( res.get( "player_id").getAsString());

                        JsonObject req = new JsonObject();
                        req.addProperty("op_code", 1);
                        out.println( gson.toJson( req));
                        break;

                    } case 1: { //receive players first time
                        Type playersListType = new TypeToken<List<Player>>() {}.getType();
                        ArrayList<Player> players = gson.fromJson( res.get("all_players").getAsString(), playersListType );
                        engine.players = players;
                        System.out.println( "Houses updated: " + players.size());

                        ArrayList<String> houses = new ArrayList<>();
                        for( Player a: engine.players){
                            houses.add( a.house.name);
                        }
                        WaitScreenController.updateHouses( houses);

                        acknowledgeRequest();
                        break;
                    } case 2: { //start game
                        System.out.println( "SERVER SAID: START GAME");
                        WaitScreenController.showMainScreen();
                        acknowledgeRequest();
                        break;
                    } case 3: { //update players
                        Type playersListType = new TypeToken<List<Player>>() {}.getType();
                        ArrayList<Player> players = gson.fromJson( res.get("all_players").getAsString(), playersListType );
                        engine.players = players;

                        acknowledgeRequest();
                        break;
                    } case 4: {
                        Scoreboard scoreboard = gson.fromJson( res.get("all_players").getAsString(), Scoreboard.class);
                        engine.scoreboard = scoreboard;

                        acknowledgeRequest();
                        break;
                    }
                    default: {
                        System.out.println( "Client: Invalid opcode");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception on client");
            System.out.println( e.getStackTrace()[0].getLineNumber()  + e.toString());
        }
    }
}

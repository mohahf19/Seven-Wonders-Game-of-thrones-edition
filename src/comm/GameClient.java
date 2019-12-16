package comm;

import backend.app.constants;
import backend.controllers.WaitScreenController;
import backend.models.Player;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

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


    public void connectClient() {
        try {

            JsonObject ob = new JsonObject();
            ob.addProperty("op_code", 0);
            out.println( gson.toJson( ob));

            while (true) {
                String response = in.readLine();
                System.out.println("data received on client: " + response);

                JsonObject res = gson.fromJson( response, JsonObject.class);

                int op = Integer.parseInt( res.get( "op_code").getAsString());
                switch ( op) {
                    case 0: { //received player details
                        Player player = gson.fromJson( "" + res.get( "player").getAsString(), Player.class);
                        id = player.id;
                        System.out.println( player.house.name);

                        JsonObject req = new JsonObject();
                        req.addProperty("op_code", 1);
                        out.println( gson.toJson( req));
                        break;
                    } case 1: { //current id received, request houses
                        String housesRes = res.get("all_houses").getAsString();
                        String[] temp = housesRes.split( ",");
                        ArrayList<String> houses = new ArrayList<>();
                        for( String a: temp){
                            houses.add( a);
                        }
                        WaitScreenController.updateHouses( houses);
                        break;
                    } case 2: { //request game start
                        JsonObject req = new JsonObject();
                        req.addProperty("op_code", 2);
                        out.println( gson.toJson( req));

                        break;
                    } case 3: { //start game
                        System.out.println( "SERVER SAID: START GAME");
                        WaitScreenController.showMainScreen();
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

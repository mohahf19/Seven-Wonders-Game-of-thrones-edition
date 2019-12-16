package comm;

import backend.app.Main;
import backend.app.constants;
import backend.models.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameHost {
    public ServerController serverController;

    public ArrayList<ClientThread> clients;
    public ServerSocket serverSocket;
    public boolean isReceiving = true;
    public String serverIP="";
    private Gson gson;

    public GameHost( ServerController controller){
        this.serverController = controller;
        gson = new Gson();
        clients = new ArrayList<>();
    }



    public void startServer() {
        String port = constants.PORT_NO;
        try {

            int portNo = Integer.valueOf(port);
            serverSocket = new ServerSocket(portNo, 0, InetAddress.getLocalHost());
            System.out.println(serverSocket);

            System.out.println(serverSocket.getInetAddress().getHostName() + ":"
                    + serverSocket.getLocalPort() + " : " + serverSocket.getInetAddress().getHostAddress());

            serverIP = "" + serverSocket.getInetAddress().getHostAddress();

            addCurrentClient( "" + serverSocket.getInetAddress().getHostAddress());


            while (isReceiving ) {
                Socket socket = serverSocket.accept();
                if( clients.size() < 7) {
                    clients.add(new ClientThread(clients.size(), socket, this));

                    serverController.initHouse();

                }
                else {
                    isReceiving = false;
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println( "Player limit reached");
                }
            }
        } catch (IOException e) {
            System.out.println("IO Exception:" + e);
        } catch (NumberFormatException e) {
            System.out.println("Number Format Exception:" + e);
        }
    }

    public void addCurrentClient( String ip) {
        (new Thread(() -> {
            Main.gameEngine.client = new GameClient( ip, Main.gameEngine);
            Main.gameEngine.client.startClient();
        })).start();
    }

    public void sendRequest( int id, JsonObject request){
        this.clients.get( id).sendRequestToClient( request);
    }

    public void receiveRequest( int id, JsonObject request){
        int op = Integer.parseInt( request.get( "op_code").getAsString());
        switch ( op) {
            case 0: { //client identified
                Player player = serverController.players.get( id);


                JsonObject outOb = new JsonObject();
                outOb.addProperty( "op_code", 0);
                outOb.addProperty( "player", gson.toJson( player, Player.class));

                sendRequest( id, outOb);

                break;
            }
            case 1: { //update houses on all clients
                serverController.updateHouses();
                break;
            } case 2: { //start game request
                isReceiving = false;

                JsonObject outOb = new JsonObject();
                outOb.addProperty( "op_code", 3);

                sendRequest( id, outOb);

                break;
            }
            default:
                System.out.println( "Invalid opcode");
        }
    }


}

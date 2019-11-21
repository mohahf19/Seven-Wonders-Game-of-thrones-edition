package backend.models;

import backend.app.Main;
import backend.app.constants;
import backend.controllers.ConnectionController;
import backend.controllers.WaitScreenController;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Server {

    public ArrayList<ClientThread> clients;
    public ArrayList<Player> players;
    public ArrayList<String> allHouses;
    public ServerSocket serverSocket;

    public boolean isReceiving = true;


    public Server() {
        allHouses = new ArrayList<>();
        allHouses.add("Lannister");
        allHouses.add("Stark");
        allHouses.add("White Walker");
        allHouses.add("Baratheon");
        allHouses.add("Targaryen");
        allHouses.add("Greyjoy");
        allHouses.add("Tyrell");

        clients = new ArrayList<>();
        players = new ArrayList<>();
    }


    public void startServer() {
        String port = constants.PORT_NO;

        try {

            int portNo = Integer.valueOf(port);
            serverSocket = new ServerSocket(portNo, 0, InetAddress.getLocalHost());
            System.out.println(serverSocket);

            System.out.println(serverSocket.getInetAddress().getHostName() + ":"
                    + serverSocket.getLocalPort() + " : " + serverSocket.getInetAddress().getHostAddress());

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.NONE, "Server IP: " + serverSocket.getInetAddress().getHostAddress(), ButtonType.CLOSE);
                    alert.showAndWait();
                }
            });


            addCurrentClient( "" + serverSocket.getInetAddress().getHostAddress());


            while (isReceiving ) {
                Socket socket = serverSocket.accept();
                if( clients.size() < 7) {
                    clients.add(new ClientThread(clients.size(), socket));
                    int index = (int) (Math.random() * allHouses.size());

                    Player newPlayer = new Player();
                    newPlayer.house = new House( allHouses.get( index));
                    newPlayer.id = players.size();
                    players.add( newPlayer);
                    allHouses.remove( index);
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
            Main.game.conn.client = new Client( ip);
            Main.game.conn.client.startClient();
        })).start();
        (new Thread(() -> Main.game.conn.client = new Client( ip))).start();
    }

}
package backend.models;

import backend.app.Main;
import backend.app.constants;
import backend.controllers.ConnectionController;

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
    public ServerSocket serverSocket;



    public Server() {
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

            addCurrentClient( "" + serverSocket.getInetAddress().getHostAddress());

            while (true ) {
                Socket socket = serverSocket.accept();
                if( clients.size() < 7) {
                    clients.add(new ClientThread(clients.size(), socket));
                    System.out.println( "clientthread added");
                }
                else {
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
package backend.models;

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

    private ArrayList<ClientThread> clients = new ArrayList<>();
    private ServerSocket serverSocket;

    private String clientName = "";


    public Server( String clientName) {
        this.clientName = clientName;
        startServer();
    }

    public void startServer() {
        String port = constants.PORT_NO;

        try {

            int portNo = Integer.valueOf(port);
            serverSocket = new ServerSocket(portNo, 0, InetAddress.getLocalHost());
            System.out.println(serverSocket);

            System.out.println(serverSocket.getInetAddress().getHostName() + ":"
                    + serverSocket.getLocalPort() + " : " + serverSocket.getInetAddress().getHostAddress());

            addCurrentClient( clientName, "" + serverSocket.getInetAddress().getHostAddress());

            while (true ) {
                Socket socket = serverSocket.accept();
                if( clients.size() < 7)
                    clients.add( new ClientThread(socket));
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

    public void addCurrentClient( String uName, String ip) {
        (new Thread(() -> ConnectionController.client = new Client( uName, ip))).start();
    }

}
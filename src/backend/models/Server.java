package backend.models;

import backend.app.constants;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Server implements Runnable {

    private ArrayList<ClientThread> clients = new ArrayList<>();
    private ServerSocket serverSocket;

    private Thread thread;
    private volatile boolean isRunning = true;

    public Server() {
        startServer();
    }

    public void startServer() {
        thread = new Thread(this);
        thread.start();
    }

    public void run() {

        String port = constants.PORT_NO;

        try {

            int portNo = Integer.valueOf(port);
            serverSocket = new ServerSocket(portNo, 0, InetAddress.getLocalHost());
            System.out.println(serverSocket);

            System.out.println(serverSocket.getInetAddress().getHostName() + ":"
                    + serverSocket.getLocalPort());

            while (isRunning && true ) {
                Socket socket = serverSocket.accept();
                clients.add( new ClientThread(socket));
            }
        } catch (IOException e) {
            System.out.println("IO Exception:" + e);
            isRunning = false;
        } catch (NumberFormatException e) {
            System.out.println("Number Format Exception:" + e);
            isRunning = false;
        }
    }

}
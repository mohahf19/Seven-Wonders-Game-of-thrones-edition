package backend.models;

import backend.app.constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class Client {
    private String userName;
    private String serverAddress;


    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Client( String name, String server) {
        userName = name;
        serverAddress = server;

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

            out.println("" + userName);

            while (true) {
                String response = in.readLine();
                System.out.println( "Response from server: " + response);
            }
        } catch (Exception e) {
            System.out.println("Client is dead...");
        }
    }

}
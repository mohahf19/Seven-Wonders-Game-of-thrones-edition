package backend.models;

import backend.app.constants;
import backend.controllers.WaitScreenController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class Client {
    public String serverAddress;


    public Socket socket;
    public BufferedReader in;
    public PrintWriter out;

    public int id = -1;

    public Client( String server) {
        serverAddress = server;
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

            out.println("*connection requested");

            while (true) {
                String response = in.readLine();
                if( response.charAt(0) == '*'){
                    id = Integer.parseInt(response.substring( 1));
                    out.println( "" + id + "gethouses");
                } else {
                    String[] temp = response.split( ",");
                    ArrayList<String> houses = new ArrayList<>();
                    for( String a: temp){
                        houses.add( a);
                    }
                    WaitScreenController.updateHouses( houses);
                }
                System.out.println( "Response from server: " + response);
            }
        } catch (Exception e) {
            System.out.println("Client is dead...");
        }
    }

}
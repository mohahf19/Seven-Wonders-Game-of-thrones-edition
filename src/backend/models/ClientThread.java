package backend.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ClientThread implements Runnable {


    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private String test;

    private Thread thread;

    private volatile boolean isRunning = true;


    public ClientThread(Socket socket) {
        try {
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

                test = in.readLine();
                System.out.println("data received on server: " + test);
                out.println( "Lannister alotted to " + test);
            }

            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

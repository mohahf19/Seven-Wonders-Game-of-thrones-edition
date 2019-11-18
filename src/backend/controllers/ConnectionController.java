package backend.controllers;

import backend.app.Main;
import backend.models.Client;
import backend.models.ClientThread;
import backend.models.Server;

import java.util.ArrayList;

public class ConnectionController {
    public Server server;
    public Client client;

    public static int state = 1; //0 for server, 1 for client

    public void initServer(){
        (new Thread(() -> {
            this.server = new Server();
            this.server.startServer();
        })).start();
        state = 0;
    }
    public void initClient( String ip){
        server = null;
        (new Thread(() -> {
            this.client = new Client( ip);
            this.client.startClient();
        })).start();
        state = 1;
    }
    public void updateHouses(){
        ArrayList<ClientThread> threads = this.server.clients;
        for( ClientThread th: threads){
            th.out.println( "Lannister,Khan,Fakhouri");
        }
    }
}

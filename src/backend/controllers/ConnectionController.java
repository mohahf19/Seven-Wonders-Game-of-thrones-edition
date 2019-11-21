package backend.controllers;

import backend.app.Main;
import backend.models.Client;
import backend.models.ClientThread;
import backend.models.House;
import backend.models.Server;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.lang.reflect.GenericSignatureFormatError;
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

        for( int i = 0; i < threads.size(); i++){
            ClientThread cur = threads.get( i);
            String out = this.server.players.get(i).house.name;
            for( int j = 0; j < this.server.players.size(); j++){
                if( j != i)
                    out += "," + this.server.players.get(j).house.name;
            }

            JsonObject outOb = new JsonObject();
            outOb.addProperty( "op_code", 1);
            outOb.addProperty( "all_houses", out);

            cur.out.println( new Gson().toJson(outOb));
        }
    }

    public void startGameRequest(){
        ArrayList<ClientThread> threads = this.server.clients;
        for( int i = 0; i < threads.size(); i++){
            ClientThread cur = threads.get( i);

            JsonObject outOb = new JsonObject();
            outOb.addProperty( "op_code", 2);
            cur.out.println( new Gson().toJson(outOb));
        }
    }
}

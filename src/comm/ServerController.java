package comm;

import backend.models.House;
import backend.models.Player;
import backend.models.Scoreboard;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ServerController {

    public GameHost host;

    public ArrayList<Player> players;
    public ArrayList<House> allHouses;

    public Scoreboard scoreboard;

    private Gson gson;

    public ServerController(){
        gson = new Gson();
        initHouses();
    }

    public void initHouses(){
        allHouses = new ArrayList<>();
        populateHouses();
        players = new ArrayList<>();
    }

    public void initServer(){
        (new Thread(() -> {
            this.host = new GameHost( this);
            this.host.startServer();
        })).start();
    }

    public void initHouse(){
        int index = (int) (Math.random() * allHouses.size());
        Player newPlayer = new Player();
        newPlayer.house = allHouses.get(index);
        newPlayer.id = players.size();
        players.add( newPlayer);
        allHouses.remove( index);
    }

    public void populateHouses() {

        Type houseListType = new TypeToken<List<House>>() {}.getType();
        try {
            ArrayList<House> houses = gson.fromJson( new BufferedReader(new FileReader("src\\assets\\houses.json")) , houseListType );
            System.out.println( "Houses found " + houses.size());
            allHouses = houses;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sendHouseJoined(){
        for( int i = 0; i < host.clients.size(); i++){

            JsonObject outOb = new JsonObject();
            outOb.addProperty( "op_code", 1);
            outOb.addProperty( "all_players", gson.toJson(this.players));

            host.sendRequest( i, outOb);
        }
    }

    public void sendHouses(){
        for( int i = 0; i < host.clients.size(); i++){

            JsonObject outOb = new JsonObject();
            outOb.addProperty( "op_code", 3);
            outOb.addProperty( "all_players", gson.toJson(this.players));

            host.sendRequest( i, outOb);
        }
    }

    public void sendScoreboard(){
        for( int i = 0; i < host.clients.size(); i++){

            JsonObject outOb = new JsonObject();
            outOb.addProperty( "op_code", 4);
            outOb.addProperty( "scoreboard", gson.toJson(this.scoreboard));

            host.sendRequest( i, outOb);
        }
    }

    public void startGame(){
        if( !host.requestsAcknowledged())
            return;

        host.isReceiving = false;
        for( int i = 0; i < host.clients.size(); i++){

            JsonObject outOb = new JsonObject();
            outOb.addProperty( "op_code", 2);

            host.sendRequest( i, outOb);
        }
    }
}

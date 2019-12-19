package comm;

import backend.models.Age;
import backend.models.House;
import backend.models.Player;
import backend.models.Scoreboard;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;


import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ServerController {

    public GameHost host;
    private Gson gson;

    public ArrayList<Player> players;
    public ArrayList<House> allHouses;
    public Scoreboard scoreboard;

    public ArrayList<Age> ages;
    public int currentAge = -1;
    public int currentSeason;

    public int cardsSelectedCount = 0;

    public ServerController(){
        gson = new Gson();
        initData();
    }

    public void initData(){
        //initialize lists
        players = new ArrayList<>();
        allHouses = new ArrayList<>();
        ages = new ArrayList<>();

        //populate houses
        populateHouses();

        //populate Ages
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

    public void incrementAge(){
        if( currentAge < 2) {
            currentAge++;
            for( int i = 0; i < host.clients.size(); i++){

                JsonObject outOb = new JsonObject();
                outOb.addProperty( "op_code", 6);
                outOb.addProperty( "age", currentAge + 1);

                host.sendRequest( i, outOb);
            }
            playTurn();

        } else {
            //game ended
        }
    }
    public void changeSeason(){
        int season = ((int)(Math.random() * 4)) + 1;
        for( int i = 0; i < host.clients.size(); i++){

            JsonObject outOb = new JsonObject();
            outOb.addProperty( "op_code", 5);
            outOb.addProperty( "season", season);

            host.sendRequest( i, outOb);
        }
    }

    public void playTurn(){
        cardsSelectedCount = 0;

        //update houses
        //update deck

        changeSeason();

        //sendHouses();
        //sendScoreboard();
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

    public void updatePlayer( Player player, int id){
        this.players.set( id, player);
    }

    public void startGame(){
//        if( !host.requestsAcknowledged())
//            return;
        host.isReceiving = false;

        for( int i = 0; i < host.clients.size(); i++){

            JsonObject outOb = new JsonObject();
            outOb.addProperty( "op_code", 2);

            host.sendRequest( i, outOb);
        }
    }

    public void viewInitialized(){
        cardsSelectedCount++;
        if (cardsSelectedCount >= players.size() - 1){
            cardsSelectedCount = 0;
            incrementAge();
        }
    }
}

//        class UpdateSeason extends TimerTask {
//            public void run() {
//                changeSeason();
//            }
//        }
//        Timer timer = new Timer();
//        timer.schedule(new UpdateSeason(), 0, 3000);

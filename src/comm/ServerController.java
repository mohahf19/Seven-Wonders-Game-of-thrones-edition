package comm;

import backend.controllers.PlayScreenController;
import backend.models.*;
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

import static backend.models.Numbers.arr;

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

    public void populateAges(){

        try{
//            Deck deck1 = new Deck( players.size(), 1);
//            Deck deck2= new Deck( players.size(), 2);
//            Deck deck3 = new Deck( players.size(), 3);

            Deck deck1 = new Deck( 3, 1);
            Deck deck2= new Deck( 3, 2);
            Deck deck3 = new Deck( 3, 3);

            ages.add( new Age( deck1));
            ages.add( new Age( deck2));
            ages.add( new Age( deck3));
        } catch ( Exception e){
            System.out.println( "EXCEPTION::::" + e.toString() + e.getStackTrace());
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

    public void shuffleCards(){
        ArrayList<Card> cards = ages.get( currentAge).getDeck().getCards();
        int start = 0;
        for( int i = 0; i < players.size(); i++){
            players.get( i).cards = new ArrayList<>( cards.subList( start, start + 7));
            start += 7;
        }
    }

    public void playTurn(){
        cardsSelectedCount = 0;
        changeSeason();

        //shuffle cards around
        shuffleCards();

        //update houses
        sendHouses();

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
            populateAges();
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

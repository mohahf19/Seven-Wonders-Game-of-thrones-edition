package backend.controllers;

import backend.models.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import comm.GameClient;

import java.util.ArrayList;

public class GameEngine {
    public GameClient client;
    public Gson gson;

    public ArrayList<Player> players;
    public int currentSeason;
    public int currentAge;
    public Scoreboard scoreboard;

    public GameEngine(){
        gson = new Gson();
    }


    public void initClient( String ip){
        (new Thread(() -> {
            this.client = new GameClient( ip, this);
            this.client.startClient();
        })).start();
    }

    public Player getCurrentPlayer(){
        return this.players.get( this.client.id);
    }

    public int checkCardRequirements( Card card){
        return 1;
    }

    public void updateSeason( int season){
        this.currentSeason = season;
        PlayScreenController.updateSeasonImage( season);
    }
    public void updateAge( int age){
        this.currentAge = age;
        PlayScreenController.updateAgeImage( age);
    }

    public void startMilitaryConflict(){}

    public void chooseCard( Card card){
        this.playCard(card);
        JsonObject req = new JsonObject();
        req.addProperty("op_code", 2);
        req.addProperty("player", gson.toJson( getCurrentPlayer()));
        client.sendRequest( req);
    }

    public void playCard( Card card){
        if (card.isResource()) {
            this.getCurrentPlayer().playResource((Resource) card);
        }
        else if (card.isMilitary()) {
            this.getCurrentPlayer().playMilitary((Military) card);
        }
        else if (card.isCommerce()) {
            this.getCurrentPlayer().playCommerce((Commerce) card);
        }
        else if (card.isScience()) {
            this.getCurrentPlayer().playScience((Science) card);
        }
        else if (card.isCivic()) {
            this.getCurrentPlayer().playCivic((Civic) card);
        }
        else if (card.isCrisis()) {
            this.getCurrentPlayer().playCrisis((Crisis) card);
        }
        else {
            System.out.println("Failed to determine the type of the card");
            System.exit(1);
        }
        this.getCurrentPlayer().house.playedCards.add(card);
    }

}

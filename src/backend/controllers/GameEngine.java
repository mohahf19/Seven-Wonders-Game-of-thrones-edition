package backend.controllers;

import backend.app.Main;
import backend.models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import comm.GameClient;
import comm.PlayerDeserializer;

import java.util.ArrayList;

public class GameEngine {
    public GameClient client;
    public Gson gson;
    private Gson playerGson;

    public ArrayList<Player> players;
    public int currentSeason; //1=Summer, 2=Autumn, 3=Winter, 4=Spring
    public int currentAge = 1;
    public Scoreboard scoreboard;

    public GameEngine(){
        gson = new Gson();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Player.class, new PlayerDeserializer());
        playerGson = gsonBuilder.create();

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
    public void updateScoreboard( Scoreboard scoreboard){
        this.scoreboard = scoreboard;
        PlayScreenController.updateScoreboard(scoreboard);
    }
    public void updateNeighbors(){
        for( int i = 0; i < players.size(); i++){
            Player p = players.get( i);
            int leftIndex = (i - 1) < 0 ? (players.size() - 1) : (i - 1);
            int rightIndex = (i + 1) > (players.size() - 1) ? 0 : (i + 1);
            p.neighbors.left = players.get( leftIndex);
            p.neighbors.right = players.get( rightIndex);
        }
    }

    public void endGame(){
        PlayScreenController.endGame();
    }

    public void sendWarStarted(){
        // TODO: fill this
    }

    public void discardCard(int cardIndex) {
        //TODO: remove the card from the cards arraylist
        this.getCurrentPlayer().house.coins += 3;
        this.cardPlayed(cardIndex);
    }

    public void buildWonder(int cardIndex) {
        //TODO this must support trading for wonder's resources
        //this card must not appear in the playedCard arraylist
        //remember to remove the card from the cards array list
        this.getCurrentPlayer().house.buildWonder();
        this.cardPlayed(cardIndex);
    }

    public void playCard( int cardIndex){
        Card card = getCurrentPlayer().cards.get( cardIndex);
        if( card == null)
            return;

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
            this.sendWarStarted();
        } else {
            System.out.println("Failed to determine the type of the card");
            // do something
        }
        this.getCurrentPlayer().getPlayedCards().add(card);
        this.cardPlayed(cardIndex);
    }

    public void cardPlayed(int cardIndex) {
        this.getCurrentPlayer().getCardsInHand().remove(cardIndex);
        getCurrentPlayer().neighbors.left = null;
        getCurrentPlayer().neighbors.right = null;

        JsonObject req = new JsonObject();
        req.addProperty("op_code", 2);
        req.addProperty("player", gson.toJson( getCurrentPlayer()));

        Main.gameEngine.client.sendRequest( req);
    }

    public void summerInEffect(){ //1
        /*for(Player p: players) {
            if (p.house.name.equalsIgnoreCase("stark"))
                //halve resources
        }*/
    }

    public void springInEffect(){ //4
        /*for(Player p: players) {
            if (p.house.name.equalsIgnoreCase("tyrell"))
            //trading change
        }*/
    }

    public void winterInEffect(){ //3

    }

    public void autumnInEffect(){ //2

    }

    public int canBuild(Card card) {
        return getCurrentPlayer().canBuild(card);
//        return 1;
    }

    //precondition: current player needs (and can) trade with left neighbor
    public void tradeLeft(int index) {
        Card card = getCurrentPlayer().cards.get(index);
        //TODO trade with left
        playCard(index);
        //TODO add the played card to the playedCard array
        //TODO remove the played card from the cards array
        //TODO pay left
    }

    //precondition: current player needs (and can) trade with right neighbor
    public void tradeRight(int index) {
        Card card = getCurrentPlayer().cards.get(index);
        //TODO trade with right
        playCard(index);
        //TODO add the played card to the playedCard array
        //TODO remove the played card from the cards array
        //TODO pay right
    }

    public int canBuildWonder() {
        //TODO check if wonder needs to be traded for.
        //if trade is not required, return 1
        //if trade is required, return 0.
        //otherwise, return 0
        return 1;
    }

    public String getCoins(){
        return "" + getCurrentPlayer().getCoins();
    }

    public String getShields(){
        return "" + getCurrentPlayer().getShieldCount();
    }

    public ArrayList<Card> getCards(){
        return getCurrentPlayer().cards;
    }

    public ArrayList<Card> getPlayedCards(){
        return getCurrentPlayer().getPlayedCards();
    }
}

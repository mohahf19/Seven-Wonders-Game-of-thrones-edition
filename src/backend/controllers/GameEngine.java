package backend.controllers;

import backend.app.Main;
import backend.app.fxmlPaths;
import backend.models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import comm.GameClient;
import comm.PlayerDeserializer;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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

    public void showMilitaryConflict(){
        PlayScreenController.showMilitaryConflict();
        SoundController.playBattleSound();
        new Thread(new Runnable() {
            public void run() {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        PlayScreenController.hideMilitaryConflict();
                    }
                }, 3000);
            }
        }).start();

    }
    public void startMilitaryConflict( int cardIndex){
        this.getCurrentPlayer().cards.remove(cardIndex);

        getCurrentPlayer().neighbors.left = null;
        getCurrentPlayer().neighbors.right = null;

        JsonObject req = new JsonObject();
        req.addProperty("op_code", 4);
        req.addProperty("player", gson.toJson( getCurrentPlayer()));

        Main.gameEngine.client.sendRequest( req);
    }

    public void discardCard(int cardIndex) {
        if( this.currentSeason == 3 && (this.getCurrentPlayer().house.name.equalsIgnoreCase("stark") || this.getCurrentPlayer().house.name.equalsIgnoreCase("white walkers")))
            this.getCurrentPlayer().house.coins += 5;
        else
            this.getCurrentPlayer().house.coins += 3;

        this.cardPlayed(cardIndex);
    }

    public void buildWonder(int cardIndex) {
        this.getCurrentPlayer().house.buildWonder();
        this.cardPlayed(cardIndex);
    }

    public void playCard( int cardIndex){
        Card card = getCurrentPlayer().cards.get( cardIndex);
        getCurrentPlayer().house.coins -= card.cost.coins;
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
            this.getCurrentPlayer().getPlayedCards().add(card);
            this.startMilitaryConflict( cardIndex);
            return;
        } else {
            System.out.println("Failed to determine the type of the card");
            // do something
        }
        this.getCurrentPlayer().getPlayedCards().add(card);
        this.cardPlayed(cardIndex);
    }

    public void cardPlayed(int cardIndex) {
        System.out.println("CARDPLAYED CAR, size: " + this.getCurrentPlayer().cards.size());

        this.getCurrentPlayer().cards.remove(cardIndex);

        System.out.println("AFTER CALL, size: " + this.getCurrentPlayer().cards.size());

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
    }

    //precondition: current player needs (and can) trade with left neighbor
    public void tradeLeft(int index) {
        Card card = getCurrentPlayer().cards.get(index);
        payTrade(0, card.getCost());
        playCard(index);
    }

    //precondition: current player needs (and can) trade with right neighbor
    public void tradeRight(int index) {
        Card card = getCurrentPlayer().cards.get(index);
        payTrade(1, card.getCost());
        playCard(index);
    }

    public void payTrade(int neighbor, Cost cost) {
        // 0 - left
        // 1 - right
        CostResult costResult = this.getCurrentPlayer().house.canAfford(cost);
        ArrayList<Integer> resourcesToBuy = this.getCurrentPlayer().factorizeResources(costResult.remaining);
        int payment = 0;
        for (Integer res : resourcesToBuy) {
            if (neighbor == 0) {
                payment += this.getCurrentPlayer().agreements.getLeft().getCost(res);
                int leftIndex = (getCurrentPlayer().id - 1) < 0 ? (players.size() - 1) : (getCurrentPlayer().id - 1);
                notifyTrade( leftIndex,  payment);
            }
            else {
                payment += this.getCurrentPlayer().agreements.getRight().getCost(res);

                int rightIndex = (getCurrentPlayer().id + 1) > (players.size() - 1) ? 0 : (getCurrentPlayer().id + 1);
                notifyTrade( rightIndex,  payment);
            }
        }
        this.getCurrentPlayer().house.addCoins((-1 * payment));
    }

    //if trade is not required, return 1
    //if trade is required, return 2.
    //otherwise, return 0
    public int canBuildWonder() {

        Wonder wonderToBuild = null;
        for (Wonder wonder : this.getCurrentPlayer().house.getWonders()) {
            if (!wonder.isBuilt()) {
                wonderToBuild = wonder;
                break;
            }
        }
        if (wonderToBuild == null)
            return 0;

        int ret = this.getCurrentPlayer().canBuild(wonderToBuild.getCost());
        if (ret <= 1)
            return ret;
        else
            return 2;
    }

    public void notifyTrade(int p, int cost){

        JsonObject req = new JsonObject();
        req.addProperty("op_code", 5);
        req.addProperty("player_id", p);
        req.addProperty("cost", cost);
        Main.gameEngine.client.sendRequest( req);
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

    public static void requestQuitGame(){
        JsonObject req = new JsonObject();
        req.addProperty("op_code", 6);
        Main.gameEngine.client.sendRequest( req);
    }
    public void quitGame(){
        //Do something to quit game
        JOptionPane.showMessageDialog( null, "Game ended  because a player quit the game.", "", JOptionPane.PLAIN_MESSAGE);
        if( Main.state == 0){
            //is client
            Main.gameEngine.client.quitGame();
            Main.gameEngine = null;
        } else {
            //is server
            Main.gameEngine.client.quitGame();
            Main.serverController.host.quitHost();
            Main.serverController = null;
            Main.gameEngine = null;
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {

                    Stage stage = (Stage) Main.window.getScene().getWindow();
                    stage.getScene().setRoot(Main.mainScreen);
                    stage.sizeToScene();
                } catch (Exception e){
                    System.out.println("Exception");
                    e.printStackTrace();
                }
            }});
    }
}

package backend.controllers;

import backend.controllers.PlayScreenController;
import backend.models.*;
import comm.GameClient;

import java.util.ArrayList;

public class GameEngine {
    public GameClient client;

    public ArrayList<Player> players;
    public int currentSeason;
    public int currentAge;
    public Scoreboard scoreboard;


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

    public void playTurn(){}
    public void playAge( int age ){
        this.currentAge = age;
    }
    public void startMilitaryConflict(){}

    public void chooseCard( Card card){}

    public void playCard( Card card){
        if (card.isResource()) {
            playResource(card);
        }
        else if (card.isMilitary()) {
            playMilitary(card);
        }
        else if (card.isCommerce()) {
            playCommerce(card);
        }
        else if (card.isScience()) {
            playScience(card);
        }
        else if (card.isCivic()) {
            playCivic(card);
        }
        else if (card.isCrisis()) {
            playCrisis(card);
        }
        else {
            System.out.println("Failed to determine the type of the card");
            System.exit(1);
        }
        this.getCurrentPlayer().house.playedCards.add(card);
    }
    public void playResource( Card card){
        this.getCurrentPlayer().updateResources(card);
    }

    public void playMilitary( Card card){
        this.getCurrentPlayer().updateMilitaryShields(card);
    }
    public void playCommerce( Card card){
        this.getCurrentPlayer().updateResources(card);
        this.getCurrentPlayer().updateVictoryPoints(card);
    }
    public void playScience( Card card){}
    public void playCivic( Card card){}
    public void playCrisis( Card card){}
}

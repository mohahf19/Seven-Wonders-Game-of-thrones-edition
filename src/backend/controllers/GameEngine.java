package backend.controllers;

import backend.controllers.PlayScreenController;
import backend.models.Age;
import backend.models.Card;
import backend.models.Player;
import backend.models.Scoreboard;
import comm.GameClient;

import java.util.ArrayList;

public class GameEngine {
    public GameClient client;

    public ArrayList<Player> players;
    public int currentSeason;
    public Age currentAge;
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

    public void playResource( Card card){}
    public void playMilitary( Card card){}
    public void playTrading( Card card){}
    public void playScience( Card card){}
    public void playCivic( Card card){}
    public void playCrisis( Card card){}
}

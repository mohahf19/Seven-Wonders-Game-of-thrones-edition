package backend.controllers;

import backend.controllers.PlayScreenController;
import backend.models.Player;
import backend.models.Scoreboard;
import comm.GameClient;

import java.util.ArrayList;

public class GameEngine {
    public GameClient client;

    public ArrayList<Player> players;
    public Scoreboard scoreboard;


    public void initClient( String ip){
        (new Thread(() -> {
            this.client = new GameClient( ip, this);
            this.client.startClient();
        })).start();
    }
}

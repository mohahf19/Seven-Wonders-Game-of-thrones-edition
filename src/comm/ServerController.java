package comm;

import backend.models.House;
import backend.models.Player;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class ServerController {

    public GameHost host;

    public ArrayList<Player> players;
    public ArrayList<String> allHouses;

    public ServerController(){
        initHouses();
    }

    public void initHouses(){
        allHouses = new ArrayList<>();
        allHouses.add("Lannister");
        allHouses.add("Stark");
        allHouses.add("White Walker");
        allHouses.add("Baratheon");
        allHouses.add("Targaryen");
        allHouses.add("Greyjoy");
        allHouses.add("Tyrell");

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
        newPlayer.house = new House( allHouses.get( index));
        newPlayer.id = players.size();
        players.add( newPlayer);
        allHouses.remove( index);
    }

    public void updateHouses(){

        for( int i = 0; i < host.clients.size(); i++){
            String out = this.players.get(i).house.name;
            for( int j = 0; j < this.players.size(); j++){
                if( j != i)
                    out += "," + this.players.get(j).house.name;
            }

            JsonObject outOb = new JsonObject();
            outOb.addProperty( "op_code", 1);
            outOb.addProperty( "all_houses", out);

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

package backend.controllers;

import backend.models.House;
import backend.models.Player;
import backend.models.Scoreboard;
import com.google.gson.*;
import comm.GameClient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

    public House initHouse(String name) {
//        ArrayList<Integer> resourceList = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,1,0));
//
//        Wonder wonder1 = new Wonder(new Cost(0,"", 0), new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0)), 0, 0, 3);
//        Wonder wonder2 = new Wonder(new Cost(0,"", 0), new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0)), 9, 0, 0);
//        Wonder wonder3 = new Wonder(new Cost(0,"", 0), new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0)), 0, 0, 7);
//        ArrayList<Wonder> wonders = new ArrayList<Wonder>(Arrays.asList(wonder1, wonder2, wonder3));
//
//        House house = new House(name, resourceList, wonders, 0, 0,0);
//        return house;
        Gson gson = new Gson();
        JSONObject jelement = null;
        try {
            jelement = (JSONObject) new JSONParser().parse(new BufferedReader(new FileReader("C:\\Users\\shkha\\IdeaProjects\\CS319-3H-SW\\src\\assets\\houses.json")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray houses = (JSONArray) jelement.get("houses");
        House house = gson.fromJson(houses.get(0).toString(), House.class);
        return house;



    }

}

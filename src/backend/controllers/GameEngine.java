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
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

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

    public void initCards() {
        Map columnToProperty = new HashMap();
        // columnToProperty.put();
    }

//    public House initHouse(String name) {
//        Gson gson = new Gson();
//        JSONArray houses = null;
//        try {
//            houses = (JSONArray) new JSONParser().parse(new BufferedReader(new FileReader("src\\assets\\houses.json")));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        House house = gson.fromJson(houses.get(0).toString(), House.class);
//        return house;
//    }

}

package backend.models;

import java.util.ArrayList;

public class Scoreboard {
    public static final int MILITARY_POINTS_INDEX = 0;
    public static final int COIN_POINTS_INDEX = 1;
    public static final int WONDER_POINTS_INDEX = 2;
    public static final int CIVIC_POINTS_INDEX = 3;
    public static final int COMMERCE_POINTS_INDEX = 4;
    public static final int SCIENCE_POINTS_INDEX = 5;
    public static final int VICTORY_POINTS_INDEX = 6;


    public ArrayList<ArrayList<Integer>> scores;

    public Scoreboard( int numOfPlayers){
        scores = new ArrayList<>();
        for( int i = 0; i < numOfPlayers; i++){
            scores.add( new ArrayList<>());

            for( int j = 0; j < 8; j++){
                scores.get( i).add( 0);
            }
        }
    }
}

package backend.models;

import java.util.ArrayList;

public class Player {
    public int id;

    public ArrayList<Card> cards;
    public Card chosenCard;
    public ArrayList<Player> neighbors;
    public House house;

    public int getShieldCount(){
        return house.getMilitaryPoints(); //? do we need shield count in house?
    }
    public int[] getResources(){
        return house.getResourcesList();
    }

    public int getCoins(){
        return house.getGold();
    }

    public ArrayList<Card> getPlayedScience(){
        Card[] playedCards = house.getPlayedCards();
        ArrayList<Card> playedScience;
        playedScience = new ArrayList<Card>();
        for(int i = 0; i < playedCards.length; i++)
        {
            if( playedCards[i].color.toString() == "green" )
                playedScience.add(playedCards[i]);
        }
        return playedScience;
    }
}

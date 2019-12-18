package backend.models;

import java.util.ArrayList;

public class Deck {
    private boolean direction;
    private ArrayList<Card> cards;

    public Deck(int noOfPlayers, int age){
        //ArrayList<Card> currAgeCards;
        //TODO ?= init Cards(age);
        /*for(int i = 0; i < currAgeCards.size(); i++)
        {
            if( currAgeCards.get(i).cardFreq <= noOfPlayers)
                cards.add(currAgeCards.get(i));
        }*/
    }
    public ArrayList<Card> getCards() {
        return cards;
    }
}

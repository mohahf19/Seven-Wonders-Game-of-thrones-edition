package backend.models;

import java.util.ArrayList;

import static backend.app.constants.*;

public class Player {
    public int id;

    public ArrayList<Card> cards;
    public Card chosenCard;
    public Neighbors neighbors;
    public House house;
    public TradingAgreements agreements;

    //constructors
    public Player(){
        id = 0;
        cards = null;
        neighbors = null;
        house = null;
        agreements = null;
    }
    public Player(House house){
        this.house = house;
        id = 0;
        cards = null;
        chosenCard = null;
        neighbors = new Neighbors();
        agreements = new TradingAgreements();
    }

    //methods

    public void setNeighbors(Player left, Player right){
        neighbors.left = left;
        neighbors.right = right;
    }


    public int getShieldCount(){
        return house.getMilitaryShields();
    }
    public ArrayList<Integer> getResources(){
        return house.getResourcesList();
    }

    public int getCoins(){
        return house.getCoins();
    }

    public ArrayList<Card> getPlayedScience(){
        Card[] playedCards = house.getPlayedCards();
        ArrayList<Card> playedScience;
        playedScience = new ArrayList<Card>();
        for(int i = 0; i < playedCards.length; i++) {
            if( playedCards[i].color.toString() == "green" )
                playedScience.add(playedCards[i]);
        }
        return playedScience;
    }

    public boolean canBuild(Cost cost){ //TODO change this to Card instead of Cost
        //if it was card, just do Cost cost = card.getCost();

        CostResult result = house.canAfford(cost);
        boolean canBuild = false;

        switch(result.code) {
            case 0:
                System.out.println("cannot build.");
            case 1:
                System.out.println("can build without trading.");
                canBuild = true;
            case 2:
                System.out.println("can build if trading works.");
                int remaining = result.remaining;
                //TODO allow for trading with left _and_ right at the same time:
                // for now, we can only trade with either

                TradingResult left = attemptTrade(neighbors.left, remaining);
                if (left.code == 1){
                    System.out.println("Can trade with left!");
                    pay(neighbors.left, agreements.left, remaining);
                    canBuild = true;
                } else{
                    TradingResult right = attemptTrade(neighbors.right, remaining);
                    if (right.code == 1){
                        System.out.println("Can trade with right!");
                        pay(neighbors.right, agreements.right , remaining);
                        canBuild = true;
                    } else{

                        System.out.println("Trading did not work:(");

                    }
                }

        }
        return canBuild;

    }

    private void pay(Player player, TradingAgreement agreement, int remaining) {
        //TODO implement the paying method in case trade is doable
        ArrayList<Integer> neededResources = factorizeResources(remaining);

        int cost = 0;
        for(int i = 0; i < neededResources.size(); i++){
            cost = cost + agreement.getCost(neededResources.get(i));
        }

        System.out.println("Cost of " + neededResources + " is " + cost);
    }

    private ArrayList<Integer> factorizeResources(int res){
        ArrayList<Integer> result = new ArrayList<>();

        int factors[] = {RES1, RES2, RES3, RES4, RES5, RES6, RES7};
        for(int i = 0; i < factors.length; i++){
            while (res % factors[i] == 0){ //2
                result.add(factors[i]);
                res/=factors[i];
            }
        }
        return result;
    }

    private TradingResult attemptTrade(Player neighbor, int res) {
        ArrayList<Integer> availResources = neighbor.house.getResourcesList();
        TradingResult result = new TradingResult();
        for(int i = 0; i < availResources.size(); i++){
            if (availResources.get(i) % res == 0){
                //then the resource is available to trade
                result.code = 1;
            }
        }
        return result;
    }

    //private classes
    private class Neighbors{
        public Player left;
        public Player right;

        public Neighbors(){
            left = null;
            right = null;
        }

        public Neighbors(Player left, Player right){
            this.left = left;
            this.right = right;
        }

        public Neighbors(ArrayList<Player> nbrs){
            left = nbrs.get(0);
            right = nbrs.get(nbrs.size() -1);
        }
    }

    private class TradingAgreements{
        TradingAgreement left;
        TradingAgreement right;

        public TradingAgreements(){
            left = new TradingAgreement();
            right = new TradingAgreement();
        }
    }
}

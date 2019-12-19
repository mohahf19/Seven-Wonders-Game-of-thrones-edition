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
    // play card methods
    public void playResource( Resource card){
        this.house.addResource(card.getResourcesList());
    }

    public void playMilitary( Military card){
        this.house.militaryShields += card.getNumberOfShields();
    }

    public void playCommerce( Commerce card){
        // wonder card
        if (card.isWonderCard()) {
            for (Wonder wonder : this.house.getWonders()) {
                if (wonder.isBuilt()) {
                    this.house.coins += card.getCoins();
                    this.house.victoryPoints += card.getVictoryPoints();
                }
            }
        }

        // commerce with cardReq
        String cardReq = card.getCardReq();
        if (cardReq != "") {
            int count = 0;
            if (card.includesLeft()) {
                for (Card c : this.neighbors.left.house.getPlayedCards()) {
                    if ((cardReq == "Resource" && c.isResource()) || (cardReq == "Commerce" && c.isCommerce())) {
                        count++;
                    }
                }
            }
            if (card.includesRight()) {
                for (Card c : this.neighbors.right.house.getPlayedCards()) {
                    if ((cardReq == "Resource" && c.isResource()) || (cardReq == "Commerce" && c.isCommerce())) {
                        count++;
                    }
                }
            }
            if (card.includesSelf()) {
                for (Card c : this.house.getPlayedCards()) {
                    if ((cardReq == "Resource" && c.isResource()) || (cardReq == "Commerce" && c.isCommerce())) {
                        count++;
                    }
                }
            }
            this.house.coins += count * card.getCoins();
            this.house.victoryPoints += count * card.getVictoryPoints();
        }

        // resources
        this.house.addResource(card.getResourceList());

        // coins if none of the above
        if (!card.isWonderCard() && cardReq == "")
            this.house.coins += card.getCoins();

        // trading effetcs
        ArrayList<Integer> tradeRes = card.getTradeRes();
        for (Integer res : tradeRes) {
            if (card.includesLeft()) agreements.left.setCost(res, 1);
            if (card.includesRight()) agreements.right.setCost(res, 1);
        }

    }
    public void playScience( Science card){
        // nothing to be done here
    }
    public void playCivic( Civic card){
        this.house.victoryPoints += card.getVictoryPoints();
        // TO DO: seasonal effects?
    }
    public void playCrisis( Crisis card){}


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
        ArrayList<Card> playedCards = house.getPlayedCards();
        ArrayList<Card> playedScience;
        playedScience = new ArrayList<Card>();

        for(int i = 0; i < playedCards.size(); i++) {
            if( playedCards.get(i).isScience() )
                playedScience.add(playedCards.get(i));
        }
        return playedScience;
    }

    //return 0 if can't build, 1 if can without trading, 2 if trading is required
    public int canBuild(Cost cost){ //TODO change this to Card instead of Cost
        //if it was card, just do Cost cost = card.getCost();
        //TODO Check card name

        CostResult result = house.canAfford(cost);
        int canBuild = 0;

        switch(result.code) {
            case 0:
                System.out.println("cannot build.");
            case 1:
                System.out.println("can build without trading.");
                canBuild = 1;
            case 2:
                System.out.println("can build if trading works.");
                int remaining = result.remaining;
                //TODO allow for trading with left _and_ right at the same time:
                // for now, we can only trade with either

                TradingResult left = attemptTrade(neighbors.left, remaining);
                if (left.code == 1){
                    System.out.println("Can trade with left!");
                    pay(neighbors.left, agreements.left, remaining); //server needs to do
                    canBuild = 2;
                } else{
                    TradingResult right = attemptTrade(neighbors.right, remaining);
                    if (right.code == 1){
                        System.out.println("Can trade with right!");
                        pay(neighbors.right, agreements.right, remaining);
                        canBuild = 2;
                    } else{
                        canBuild = 0;
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

    //private? classes
    public class Neighbors{
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

    public class TradingAgreements{
        TradingAgreement left;
        TradingAgreement right;

        public TradingAgreements(){
            left = new TradingAgreement();
            right = new TradingAgreement();
        }
    }
}

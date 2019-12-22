package backend.models;

import java.util.ArrayList;

import static backend.app.constants.*;
import static java.lang.Math.min;

public class Player {
    public int id;

    public ArrayList<Card> cards;
    public Card chosenCard;
    public Neighbors neighbors;
    public House house;
    public TradingAgreements agreements;
    public ArrayList<Card> playedCards;

    public int currentMilitaryPoints;

    //constructors
    public Player(){
        id = 0;
        cards = null;
        neighbors = new Neighbors();
        house = null;
        agreements = new TradingAgreements();
        agreements.left = new TradingAgreement();
        agreements.right = new TradingAgreement();
        currentMilitaryPoints = 0;
    }

    //methods
    public int calculateCoinPoints(){
        int coins = this.house.coins;
        return coins/3;
    }
    public int calculateWonderPoints(){
        //TODO fix this. this calculates max wonder points
        int wonderVictory = 0;
        ArrayList<Wonder> wonders = this.house.getWonders();
        for(Wonder w: wonders)
            wonderVictory += w.getVictoryPoints();
        return wonderVictory;
    }
    public int calculateCivicPoints(){
        ArrayList<Card> playedCards = this.getPlayedCards();
        int civicPoints = 0;
        for(Card c: playedCards)
        {
            if(c.isCivic())
                civicPoints += ((Civic) c).getVictoryPoints();
        }
        return civicPoints;
    }
    public int calculateCommercePoints(){
        ArrayList<Card> playedCards = this.getPlayedCards();
        int commercePoints = 0;
        int resourceCount = 0;
        int commerceCount = 0;

        for(Card c: playedCards)
        {
            if (c.isCommerce())
                commerceCount++;
            else if( c.isResource())
                resourceCount++;
        }

        for(Card c: playedCards)
        {
            if( c.isCommerce())
            {
                if( ((Commerce) c).isWonderCard())
                    commercePoints += this.house.getWonders().size()*((Commerce)c).getVictoryPoints();
                else if(((Commerce)c).getCardReq().equalsIgnoreCase("resource"))
                    commercePoints += resourceCount * ((Commerce)c).getVictoryPoints();
                else if(((Commerce)c).getCardReq().equalsIgnoreCase("commerce"))
                    commercePoints += commerceCount * ((Commerce)c).getVictoryPoints();
            }
        }

        return commercePoints;
    }
    public int calculateSciencePoints(){
        ArrayList<Card> playedCards = this.getPlayedCards();
        int sciencePoints;
        int gearCount = 0 ;
        int tabletCount = 0;
        int rulerCount = 0;
        for(Card c: playedCards)
        {
            if(c.isScience()) {
                if(((Science) c).getType().equalsIgnoreCase("ruler"))
                    rulerCount++;
                else if(((Science) c).getType().equalsIgnoreCase("tablet"))
                    tabletCount++;
                else
                    gearCount++;
            }
        }
        sciencePoints = min(rulerCount, min(gearCount, tabletCount)) * 7 +
                ((rulerCount * rulerCount)+(gearCount * gearCount)+(tabletCount * tabletCount));
        return sciencePoints;
    }

    public int calculateVictoryPoints(){
        return 0;
    }

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
        if (!cardReq.equals("")) {
            int count = 0;
            if (card.includesLeft()) {
                for (Card c : this.neighbors.left.getPlayedCards()) {
                    if ((cardReq.equals("Resource") && c.isResource()) || (cardReq.equals("Commerce") && c.isCommerce())) {
                        count++;
                    }
                }
            }
            if (card.includesRight()) {
                for (Card c : this.neighbors.right.getPlayedCards()) {
                    if ((cardReq.equals("Resource") && c.isResource()) || (cardReq.equals("Commerce") && c.isCommerce())) {
                        count++;
                    }
                }
            }
            if (card.includesSelf()) {
                for (Card c : this.getPlayedCards()) {
                    if ((cardReq.equals("Resource") && c.isResource()) || (cardReq.equals("Commerce") && c.isCommerce())) {
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
        if (!card.isWonderCard() && cardReq.equals(""))
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
        ArrayList<Card> playedCards = getPlayedCards();
        ArrayList<Card> playedScience;
        playedScience = new ArrayList<Card>();

        for(int i = 0; i < playedCards.size(); i++) {
            if( playedCards.get(i).isScience() )
                playedScience.add(playedCards.get(i));
        }
        return playedScience;
    }

    //return 0 if can't build, 1 if can without trading, 2 if left trading is required
    //3 if right trading
    public int canBuild(Card card) {
        //if card is already built, then you can't build it.
        if (alreadyBuilt(card)) {
            return 0;
        }
        Cost cost = card.getCost();

        return canBuild(cost);
    }

    public int canBuild(Cost cost) {
        CostResult result = house.canAfford(cost);
        
        int canBuild = 0;

        switch(result.code) {
            case 0:
                System.out.println("cannot build.");
                break;
            case 1:
                System.out.println("can build without trading.");
                canBuild = 1;
                break;
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
                        canBuild = 3;
                    } else{
                        canBuild = 0;
                        System.out.println("Trading did not work:(");
                    }
                }
                break;

        }
        return canBuild;

    }

    private boolean alreadyBuilt(Card card) {
        for(Card c: getPlayedCards()){
            if (card.name.equalsIgnoreCase(c.name)){
                return true;
            }
        }
        return false;
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

    public ArrayList<Integer> factorizeResources(int res){
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

    public ArrayList<Card> getCardsInHand(){
        return this.cards;
    }

    public ArrayList<Card> getPlayedCards(){
        if( playedCards == null) {
            playedCards = new ArrayList<>();
        }
        return playedCards;
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

        public TradingAgreement getLeft() {
            return left;
        }

        public TradingAgreement getRight() {
            return right;
        }
    }


}

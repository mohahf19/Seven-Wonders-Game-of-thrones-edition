package backend.models;

import backend.app.constants;

import java.util.ArrayList;

import static backend.models.Numbers.factorizeResources;

public class Commerce extends Card {
    public int victoryPoint;
    public int[] resourceList;
    public int coins;
    public int tradeRes;
    public boolean left;
    public boolean right;
    public boolean self;
    public String[] type;
    public boolean wonderCard;
    public String cardReq;


    public Commerce(String name, int cardFreq, int age, Cost cost, String imagePath,
                    String iconPath, String backPath, String chain1, String chain2,
                    int victoryPoint, int[] resourceList, int coins, int tradeRes, boolean left, boolean right, boolean self,
                    boolean wonderCard, String cardReq){

        super(name, cardFreq, age, cost, constants.path +"commerce.jpg", iconPath, constants.path +"yellow.jpg", chain1, chain2);
        this.victoryPoint = victoryPoint;
        this.coins = coins;
        this.wonderCard = wonderCard;
        this.tradeRes = tradeRes;
        this.left = left;
        this.right = right;
        this.self = self;
        this.cardReq = cardReq;

        this.resourceList = new int[resourceList.length];
        for( int i = 0; i < resourceList.length; i++)
            this.resourceList[i] = resourceList[i];

        this.type = null; //not needed really now, leave for later

        this.cardType = "commerce";

    }

    public int getVictoryPoints() {
        return victoryPoint;
    }

    public int getCoins() {
        return coins;
    }

    public int[] getResourceList() {
        return resourceList;
    }

    public ArrayList<Integer>  getTradeRes() {
        return factorizeResources(tradeRes);
    }

    public boolean includesLeft(){
        return left;
    }

    public boolean includesRight(){ return right; }

    public boolean includesSelf() {return self;}

    public boolean isWonderCard() {return wonderCard;}

    public String getCardReq() {
        return cardReq;
    }

    @Override
    public boolean isCommerce(){return true;};

}

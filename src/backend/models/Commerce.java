package backend.models;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Commerce extends Card {
    private int victoryPoint;
    private int[] resourceList;
    private int coins;
    private int[] tradingCostsLeft;
    private int[] tradingCostsRight;
    private String[] type;
    private int wonderReq;
    private Color cardColorReq;


    public Commerce(String name, int cardFreq, int age, Cost cost, String imagePath,
                    String iconPath, String backPath, String chain1, String chain2,
                    int victoryPoint, int[] resourceList, int coins, int[] tradingPost, String[] type, int wonderReq, Color cardColorReq){

        super(name, cardFreq, age, cost, imagePath, iconPath, backPath, chain1, chain2);
        this.victoryPoint = victoryPoint;
        this.coins = coins;
        this.wonderReq = wonderReq;
        this.cardColorReq = cardColorReq;

        this.resourceList = new int[resourceList.length];
        for( int i = 0; i < resourceList.length; i++)
            this.resourceList[i] = resourceList[i];

        this.tradingCostsLeft = new int[tradingPost.length];
        for( int i = 0; i < tradingPost.length; i++)
            this.tradingCostsLeft[i] = tradingPost[i];

        this.tradingCostsRight = new int[tradingPost.length];
        for( int i = 0; i < tradingPost.length; i++)
            this.tradingCostsRight[i] = tradingPost[i];

        this.type = new String[type.length];
        for( int i = 0; i < type.length; i++)
            this.type[i] = type[i];


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

    public int[] getTradingCostsRight() {
        return tradingCostsRight;
    }

    public int[] getTradingCostsLeft() {
        return tradingCostsLeft;
    }
}

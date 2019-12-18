package backend.models;

import javafx.scene.paint.Color;

public class Commerce extends Card {
    private int victoryPoints;
    private int[] resourceList;
    private int coins;
    private int[] tradingPost;
    private String[] type;
    private int wonderReq;
    private Color cardColorReq;


    public Commerce(String name, int cardFreq, int age, Cost cost, Color color, String imagePath,
                    String iconPath, String backPath, String chain1, String chain2,
                    int victoryPoint, int[] resourceList, int coins, int[] tradingPost, String[] type, int wonderReq, Color cardColorReq){

        super(name, cardFreq, age, cost, color, imagePath, iconPath, backPath, chain1, chain2);
        this.victoryPoints = victoryPoint;
        this.coins = coins;
        this.wonderReq = wonderReq;
        this.cardColorReq = cardColorReq;

        this.resourceList = new int[resourceList.length];
        for( int i = 0; i < resourceList.length; i++)
            this.resourceList[i] = resourceList[i];

        this.tradingPost = new int[tradingPost.length];
        for( int i = 0; i < tradingPost.length; i++)
            this.tradingPost[i] = tradingPost[i];

        this.type = new String[type.length];
        for( int i = 0; i < type.length; i++)
            this.type[i] = type[i];

    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public int getCoins() {
        return coins;
    }
}

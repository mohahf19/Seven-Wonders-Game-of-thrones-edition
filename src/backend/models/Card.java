package backend.models;

import javafx.scene.paint.Color;

public class Card {
    public String name;
    protected int[] cardFreq;
    protected int age;
    public Cost cost;
    public Color color;
    public String imagePath;
    public String iconPath;
    public String backPath;

    public Card(String name, int[] cardFreq, int age, Cost cost, Color color, String imagePath,
                String iconPath, String backPath){
        this.name = name;
        this.age= age;
        this.cost = cost;
        this.color = color;
        this.imagePath = imagePath;
        this.iconPath = iconPath;
        this.backPath = backPath;
        this.cardFreq = new int[cardFreq.length];
        for( int i = 0; i < cardFreq.length; i++)
            this.cardFreq[i] = cardFreq[i];
    }

    //this initializer is for ui tesintg
    public Card(String name, Cost cost, String imagePath,
                String iconPath, String backPath){
        this.name = name;
        this.cost = cost;
        this.imagePath = imagePath;
        this.iconPath = iconPath;
        this.backPath = backPath;
    }

    public Cost getCost() {
        return cost;
    }

    public boolean isResource(){
        return false;
    }

    public boolean isMilitary(){
        return false;
    }

    public boolean isScience(){
        return false;
    }

    public boolean isCivic(){
        return false;
    }

    public boolean isCommerce(){
        return false;
    }

    public boolean isCrisis(){
        return false;
    }

}

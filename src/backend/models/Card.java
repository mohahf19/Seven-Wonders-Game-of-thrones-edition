package backend.models;

import javafx.scene.paint.Color;

public class Card {
    protected String name;
    protected int[] cardFreq;
    protected int age;
    protected Cost cost;
    protected Color color;
    protected String imagePath;

    public Card(String name, int[] cardFreq, int age, Cost cost, Color color, String imagePath){
        this.name = name;
        this.age= age;
        this.cost = cost;
        this.color = color;
        this.imagePath = imagePath;
        this.cardFreq = new int[cardFreq.length];
        for( int i = 0; i < cardFreq.length; i++)
            this.cardFreq[i] = cardFreq[i];
    }


}
package backend.models;

import javafx.scene.paint.Color;

public class Crisis extends Card {
    private int crisisID;

    public Crisis(String name, int[] cardFreq, int age, Cost cost, Color color, String imagePath, int crisisID){
        super(name, cardFreq, age, cost, color, imagePath);
        this.crisisID = crisisID;
    }
}

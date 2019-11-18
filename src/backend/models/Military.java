package backend.models;

import javafx.scene.paint.Color;

public class Military extends Card{
    private int numberOfShields;

    public Military(String name, int[] cardFreq, int age, Cost cost, Color color, String imagePath, int numberOfShields){
        super(name, cardFreq, age, cost, color, imagePath);
        this.numberOfShields = numberOfShields;
    }
}

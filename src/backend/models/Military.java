package backend.models;

import javafx.scene.paint.Color;

public class Military extends Card{
    private int numberOfShields;

    public Military(String name, int[] cardFreq, int age, Cost cost, Color color, String imagePath,
                    String iconPath, String backPath, String chain1, String chain2, int numberOfShields){
        super(name, cardFreq, age, cost, color, imagePath, iconPath, backPath, chain1,chain2);
        this.numberOfShields = numberOfShields;
    }

    @Override
    public boolean isMilitary(){
        return true;
    }
}

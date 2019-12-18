package backend.models;

import javafx.scene.paint.Color;

public class Science extends Card{
    private String type;

    public Science(String name, int cardFreq, int age, Cost cost, String imagePath,
                   String iconPath, String backPath, String chain1, String chain2, String type){
        super(name, cardFreq, age, cost, imagePath, imagePath, backPath, chain1, chain2);

        this.type = type;
    }

    @Override
    public boolean isScience(){
        return true;
    }
}

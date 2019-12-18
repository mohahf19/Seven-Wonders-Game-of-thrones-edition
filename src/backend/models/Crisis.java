package backend.models;

import javafx.scene.paint.Color;

public class Crisis extends Card {
    private int crisisID;

    public Crisis(String name, int cardFreq, int age, Cost cost, String imagePath,
                  String iconPath, String backPath, int crisisID){
        super(name, cardFreq, age, cost, imagePath, iconPath, backPath, "","");
        this.crisisID = crisisID;
    }

    @Override
    public boolean isCrisis(){
        return true;
    }
}

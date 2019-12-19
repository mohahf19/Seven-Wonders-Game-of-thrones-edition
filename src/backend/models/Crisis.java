package backend.models;

import javafx.scene.paint.Color;

public class Crisis extends Card {
    private int crisisID;

    public Crisis(String name, Cost cost, String imagePath,
                  String iconPath, String backPath, int crisisID){
        super(name, 1, 3, cost, imagePath, iconPath, backPath, "","");
        this.crisisID = crisisID;
    }

    @Override
    public boolean isCrisis(){
        return true;
    }
}

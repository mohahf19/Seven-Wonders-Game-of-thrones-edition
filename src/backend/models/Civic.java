package backend.models;

import javafx.scene.paint.Color;

public class Civic extends Card {
    private int victoryPoints;
    private int seasonalEffect;

    public Civic(String name, int cardFreq, int age, Cost cost, Color color, String imagePath,
                 String iconPath, String backPath, String chain1, String chain2, int victoryPoint, int seasonalEffect){
        super(name, cardFreq, age, cost, color, imagePath, iconPath, backPath, chain1, chain2);
        this.victoryPoints = victoryPoint;
        this.seasonalEffect = seasonalEffect;
    }

    public int getSeasonalEffect() {
        return seasonalEffect;
    }
    public int getVictoryPoints() {
        return victoryPoints;
    }

    @Override
    public boolean isCivic(){
        return true;
    }
}

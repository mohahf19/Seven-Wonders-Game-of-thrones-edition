package backend.models;

import backend.app.constants;

public class Civic extends Card {
    public int victoryPoint;
    public int seasonalEffect;

    public Civic(String name, int cardFreq, int age, Cost cost, String imagePath,
                 String iconPath, String backPath, String chain1, String chain2, int victoryPoint, int seasonalEffect){

        super(name, cardFreq, age, cost, constants.path +"civic.jpg", iconPath, constants.path +"blue.jpg", chain1, chain2);
        this.victoryPoint = victoryPoint;
        this.seasonalEffect = seasonalEffect;
        this.cardType = "civic";
    }

    public int getSeasonalEffect() {
        return seasonalEffect;
    }
    public int getVictoryPoints() {
        return victoryPoint;
    }

    @Override
    public boolean isCivic(){
        return true;
    }
}

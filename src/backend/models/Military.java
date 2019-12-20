package backend.models;

import backend.app.constants;

public class Military extends Card{
    private int numberOfShields;

    public Military(String name, int cardFreq, int age, Cost cost, String imagePath,
                    String iconPath, String backPath, String chain1, String chain2, int numberOfShields){
        super(name, cardFreq, age, cost, constants.path +"military.jpg", iconPath, constants.path +"red.jpg", chain1, chain2);
        this.numberOfShields = numberOfShields;
    }

    @Override
    public boolean isMilitary(){
        return true;
    }

    public int getNumberOfShields() {
        return numberOfShields;
    }
}

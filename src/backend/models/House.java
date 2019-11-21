package backend.models;

public class House {
    public int[] resourcesList;
    public int militaryPoints;
    public Card[] playedCards;
    public int gold;
    public int buff;
    public int nerf;

    public String name;

    public House( String name){
        this.name = name;
    }

    public int[] getResourcesList() {
        return resourcesList;
    }

    public int getMilitaryPoints() {
        return militaryPoints;
    }

    public Card[] getPlayedCards() {
        return playedCards;
    }

    public int getGold() {
        return gold;
    }

    public int getBuff() {
        return buff;
    }

    public int getNerf() {
        return nerf;
    }
}

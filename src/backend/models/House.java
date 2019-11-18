package backend.models;

public class House {
    private int[] resourcesList;
    private int militaryPoints;
    private Card[] playedCards;
    private int gold;
    private int buff;
    private int nerf;

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

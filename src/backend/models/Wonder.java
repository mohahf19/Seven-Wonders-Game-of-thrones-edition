package backend.models;

import java.util.ArrayList;

public class Wonder {
    public Cost cost;
    public ArrayList<Integer> resources;
    public int coins;
    public int militaryShields;
    public int victoryPoints;
    public boolean built;

    public Wonder(Cost cost, ArrayList<Integer> resources, int coins, int militaryShields, int victoryPoints) {
        this.cost = cost;
        this.resources = resources;
        this.coins = coins;
        this.militaryShields = militaryShields;
        this.victoryPoints = victoryPoints;
        this.built = false;
    }

    public void build() {
        this.built = true;
    }

    public Cost getCost() {
        return cost;
    }

    public ArrayList<Integer> getResources() {
        return resources;
    }

    public int getCoins() {
        return coins;
    }

    public int getMilitaryShields() {
        return militaryShields;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public boolean isBuilt() {
        return built;
    }
}

package backend.models;

public class Cost {
    public int coins;
    public String prereq;
    //required resource is modeled by a single integer where every integer corresponds to one resource type
    public int resources;
    
    public Cost(int money, String prereq, int resources) {
        this.coins = money;
        this.prereq = prereq;
        this.resources = resources;
    }

    public int getCoins() {
        return coins;
    }

    public String getPrereq() {
        return prereq;
    }

    public int getResources(){
        return resources;
    }
}

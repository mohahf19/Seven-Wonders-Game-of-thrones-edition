package backend.models;

public class Cost {
    private int money;
    private String prereq;
    //required resource is modeled by a single integer where every integer corresponds to one resource type
    private int resources;
    
    public Cost(int money, String prereq, int resources) {
        this.money = money;
        this.prereq = prereq;

        this.resources = resources;
    }

    public int getMoney() {
        return money;
    }

    public String getPrereq() {
        return prereq;
    }

    public int getResources(){
        return resources;
    }
}

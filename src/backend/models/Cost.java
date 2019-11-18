package backend.models;

public class Cost {
    private int money;
    private Card prereq;
    private int[] resources;
    
    public Cost(int money, Card prereq, int[] resources) {
        this.money = money;
        this.prereq = prereq;

        this.resources = new int[resources.length];
        for( int i = 0; i < resources.length; i++)
            this.resources[i] = resources[i];
    }

}

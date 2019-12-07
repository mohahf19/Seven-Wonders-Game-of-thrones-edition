package backend.models;

import java.util.ArrayList;

public class House {
    public ArrayList<Integer> resourcesList; //1 means no resources
    public int militaryPoints;
    public Card[] playedCards;
    public int coins;
    public int buff;
    public int nerf;

    public String name;

    public House(String name){
        this.name = name;
        resourcesList = new ArrayList<>();
        //1 represents no resources
        resourcesList.add(1);
        addCoins(3);
    }

    public void addResource(ArrayList<Integer> newRes){
        if(newRes.size() > 0){
            ArrayList<Integer> tempRes = new ArrayList<Integer>(newRes.size() * resourcesList.size());
            for(int i = 0; i < newRes.size(); i++){
                for(int j = 0; j < resourcesList.size(); j++){
                    tempRes.add(resourcesList.get(j) * newRes.get(i));
                }
            }
            resourcesList = tempRes;
        }
    }

    public void addResource(int[] newRes){
        if(newRes.length > 0){
            ArrayList<Integer> tempRes = new ArrayList<Integer>(newRes.length * resourcesList.size());
            for(int i = 0; i < newRes.length; i++){
                for(int j = 0; j < resourcesList.size(); j++){
                    tempRes.add(resourcesList.get(j) * newRes[i]);
                }
            }
            resourcesList = tempRes;
        }
    }

    public void printResources(){
        System.out.println("Printing possible resources:");
        for(int i = 0; i < resourcesList.size(); i++){
            System.out.println("" + i + ": " + factorResources(resourcesList.get(i)));


        }
        System.out.println();
    }

    private String factorResources(int res){
        String string = "";
        //7 primes for 7 resources
        int factors[] = {2, 3, 5, 7, 11, 13, 17};
        for(int i = 0; i < factors.length; i++){
            int counter = 0;
            int tempres = res;
            while (tempres % factors[i] == 0){
                counter++;
                tempres/=factors[i];
            }
            string = string + counter;
            if (i != factors.length -1){
                string = string + ", ";
            }
        }

        return string;
    }

    //returns 0 if can't, 1 if it can be built without trading, 2 if it requires trading
    public int canBuild(Cost cost){
        System.out.println("Required: " + cost.getMoney() + " money and ["
                + factorResources(cost.getResources()) + "] or " + cost.getPrereq());

        //check cost.getMoney()
        boolean canAffordMoney = cost.getMoney() <= coins;

        //check cost.getPrereq()

        //checking cost.getResources().
        for(int i = 0; i <resourcesList.size(); i++){
            if (canAffordMoney &&(resourcesList.get(i) % cost.getResources() == 0)){
                System.out.println("Can build with " + factorResources(resourcesList.get(i)));
                return 1;
            } else {
                //find the remaining wanted resources
                int gcd = gcd(resourcesList.get(i), cost.getResources());
                int remaining = cost.getResources() / gcd;

                //check if neighbors have it
                System.out.println("To use [" + factorResources(resourcesList.get(i)) + "], it requires " + countPrimes(remaining)
                    +" more resource(s) which is/are [" + factorResources(remaining) +"]");
            }
        }

        //if cant pay, return false
        return 0;
    }

    private int countPrimes(int number){
        int counter = 0;
        int factors[] = {2, 3, 5, 7, 11, 13, 17};
        for(int i = 0; i < factors.length; i++){
            while (number % factors[i] == 0){
                counter++;
                number/=factors[i];
            }
        }
        return counter;
    }

    private int gcd(int a, int b){
        while (b != 0){
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public void addCoins(int coins){
        this.coins += coins;
    }


    public ArrayList<Integer> getResourcesList() {
        return resourcesList;
    }

    public int getMilitaryPoints() {
        return militaryPoints;
    }

    public Card[] getPlayedCards() {
        return playedCards;
    }

    public int getCoins() {
        return coins;
    }

    public int getBuff() {
        return buff;
    }

    public int getNerf() {
        return nerf;
    }
}

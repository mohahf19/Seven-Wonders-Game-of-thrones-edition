package backend.models;

import java.util.ArrayList;

import static backend.app.constants.*;

public class Numbers {

    //get gcd of two integers
    public static int gcd(int a, int b){
        while (b != 0){
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    //counts primes in number (up to 17)
    public static int countPrimes(int number){
        int counter = 0;
        int factors[] = {RES1, RES2, RES3, RES4, RES5, RES6, RES7};
        for(int i = 0; i < factors.length; i++){
            while (number % factors[i] == 0){
                counter++;
                number/=factors[i];
            }
        }
        return counter;
    }

    public static ArrayList<Integer> factorizeResources(int res){
        ArrayList<Integer> result = new ArrayList<>(7);

        if (res == Deck.si*Deck.d*Deck.p){
            System.out.println("building forum probably...");
        }
        int factors[] = {RES1, RES2, RES3, RES4, RES5, RES6, RES7};
        for(int i = 0; i < factors.length; i++){
            while (res % factors[i] == 0){ //2
                result.add(factors[i]);
                res/=factors[i];
            }
        }
        return result;
    }

    public static <Integer> int[] arr (int ... els){
        return els;
    }

}

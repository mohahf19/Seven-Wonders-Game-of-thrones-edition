package backend.models;

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
        int factors[] = {2, 3, 5, 7, 11, 13, 17};
        for(int i = 0; i < factors.length; i++){
            while (number % factors[i] == 0){
                counter++;
                number/=factors[i];
            }
        }
        return counter;
    }

}

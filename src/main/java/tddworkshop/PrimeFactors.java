package tddworkshop;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactors {
    public List<Integer> getPrimeFactors(int number) {
        ArrayList<Integer> factors = new ArrayList<>();

        int candidate = 2;

        while (number >= candidate){
            while (number % candidate ==0){
                factors.add(candidate);
                number /= candidate;
            }
            candidate++;
        }



        return factors;
    }
}

package problems;

import java.util.Arrays;

public class HouseRobber {
    private final int[] houses;
    private final int [] dpHouses;

    private HouseRobber(int[] houses) {
        this.houses = houses;
        this.dpHouses = new int[houses.length+1];
        Arrays.fill(dpHouses,-1);
    }

    private int maxLoot(int n){
        if (n<0) return 0;
        if (n == 0) return houses[0];
        if (dpHouses[n]!=-1) return dpHouses[n];

        final int pick = houses[n] + maxLoot(n-2);
        final int notPick =  maxLoot(n-1);
        return dpHouses[n] = Math.max(pick,notPick);
    }
    private int compute() {
        return maxLoot(houses.length-1);
    }

    public static int compute(int[] houses) {
        return new HouseRobber(houses).compute();
    }
}

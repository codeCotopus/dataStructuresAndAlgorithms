package adventofcode.aoc2022;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class Day3Test {
    @Test
    public void sampleFirstPart(){
        assertEquals(157,compute(Day3Input.SAMPLE));
    }

    @Test
    public void inputFirstPart(){
        assertEquals(8105,compute(Day3Input.INPUT));
    }

    @Test
    public void sampleSecondPart(){
        assertEquals(70,compute2(Day3Input.SAMPLE));
    }

    @Test
    public void inputSecondPart(){
       assertEquals(2363,compute2(Day3Input.INPUT));
    }


    private int compute2(String sample) {
        String [] rucksacks = sample.split("\n");
        int numberOfGroups = rucksacks.length/3;
        int index = 0;
        String[][] groups = new String[numberOfGroups][3];
        for (int i = 0; i < groups.length; i++) {
            for (int j = 0; j < groups[i].length; j++) {
                groups[i][j]=rucksacks[index++];
            }
        }
        int total =0;
        for (String [] rucksack:groups) {
            Map<Character,Integer> itemCount = new HashMap<>();
            for (String items : rucksack) {
                Set<Character> itemList = new HashSet<>();
                for (char x :items.toCharArray()) {
                    itemList.add(x);
                }

                for (char item:itemList) {
                    if (itemCount.containsKey(item))
                        itemCount.put(item,itemCount.get(item)+1);
                    else itemCount.put(item,1);
                    if (itemCount.get(item)==3) {
                        int i1 = Integer.valueOf(item) - 96;
                        if (i1<0) i1 +=58;
                        total+=i1;
                        break;
                    }
                }
            }
        }
        log.info("Total is {}",total);

        return total;
    }


    private static int compute(String sample) {
        String [] rucksacks = sample.split("\n");
        int total =0;
        for (int i = 0; i < rucksacks.length; i++) {

            Set firstContainer = new HashSet<Character>();

            char[] itemsInRucksack = rucksacks[i].toCharArray();

            for (int j = 0; j < itemsInRucksack.length; j++) {
                if (j < itemsInRucksack.length / 2) {
                    firstContainer.add(itemsInRucksack[j]);
                }
                else {
                    if (firstContainer.contains( itemsInRucksack[j])){
                        int i1 = Integer.valueOf(itemsInRucksack[j]) - 96;
                        if (i1<0) i1 +=58;
                        total+=i1;
                        break;
                    }
                }
            }
        }
        log.info("total is {}",total);
        return total;
    }

}

package adventofcode.aoc2022;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

@Slf4j
public class Day1Test {

    @Test
    public void sampleTest1() {
        computeMax(Day1Input.sampleInput);
    }

    @Test
    public void realProblem1() {
        computeMax(Day1Input.realInput);
    }


    @Test
    public void sampleTest2() {
        compute3Max(Day1Input.sampleInput);
    }


    @Test
    public void realProblem2() {
        compute3Max(Day1Input.realInput);
    }


    private void compute3Max(String input) {
        int max = 0;
        int med = 0;
        int min = 0;

        String[] elves = input.split("\n\n");

        for (String elf : elves) {
            int sum = Arrays.stream(elf.split("\n")).flatMapToInt(x -> IntStream.of(Integer.parseInt(x))).sum();
            if (sum > max) {
                min = med;
                med = max;
                max = sum;
            } else if (sum > med) {
                min = med;
                med = sum;
            } else if (sum > min) {
                min = sum;
            }
        }
        log.info("Sum of top 3 calories is {}", max + med + min);
    }

    private static void computeMax(String input) {
        String[] elves = input.split("\n\n");
        int max = 0;
        for (String elf : elves) {
            int sum = Arrays.stream(elf.split("\n")).flatMapToInt(x -> IntStream.of(Integer.parseInt(x))).sum();
            if (sum > max) max = sum;
        }
        log.info("Max calories are {}", max);
    }
}

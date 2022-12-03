package adventofcode.aoc2022;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

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


    @Test
    public void testRockPaperScissors() {
        // Sample input
        String[] strategyGuide = {
                "A Y",
                "B X",
                "C Z"
        };

        int score = 0; // Total score

        // Iterate through each round
        for (String round : strategyGuide) {
            char opponentShape = round.charAt(0); // Shape chosen by the opponent
            char playerShape = round.charAt(2); // Shape chosen by the player according to the strategy guide

            // Calculate the score for the round
            int roundScore = 0;
            if (opponentShape == playerShape) { // Draw
                roundScore = 3;
            } else if ((opponentShape == 'A' && playerShape == 'Y') ||
                    (opponentShape == 'B' && playerShape == 'X') ||
                    (opponentShape == 'C' && playerShape == 'Z')) { // Win for player
                if (playerShape == 'Y') roundScore = 2 + 6;
                else if (playerShape == 'X') roundScore = 1 + 6;
                else if (playerShape == 'Z') roundScore = 3 + 6;
            } else { // Win for opponent
                if (playerShape == 'Y') roundScore = 2;
                else if (playerShape == 'X') roundScore = 1;
                else if (playerShape == 'Z') roundScore = 3;
            }

            score += roundScore;
        }

        assertEquals(15, score);
    }
}

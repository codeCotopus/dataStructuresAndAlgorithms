package adventofcode.aoc2022;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;


@Slf4j
public class Day2Test {

    private static final Map<String, Integer> secondStrategy = Map.of(
            "A X", 3 + 0,
            "A Y", 1 + 3,
            "A Z", 2 + 6,
            "B X", 1 + 0,
            "B Y", 2 + 3,
            "B Z", 3 + 6,
            "C X", 2 + 0,
            "C Y", 3 + 3,
            "C Z", 1 + 6
    );
    private static final Map<String, Integer> firstStrategy = Map.of(
            "A X", 1 + 3,
            "A Y", 2 + 6,
            "A Z", 3 + 0,
            "B X", 1 + 0,
            "B Y", 2 + 3,
            "B Z", 3 + 6,
            "C X", 1 + 6,
            "C Y", 2 + 0,
            "C Z", 3 + 3
    );

    @Test
    public void sampleFirstPart() {
        compute(Day2Input.SAMPLE, firstStrategy);
    }

    @Test
    public void inputFirstPart() {
        compute(Day2Input.INPUT, firstStrategy);
    }

    @Test
    public void sampleSecondPart() {
        compute(Day2Input.SAMPLE, secondStrategy);
    }

    @Test
    public void inputSecondPart() {
        compute(Day2Input.INPUT, secondStrategy);
    }

    private static void compute(String sample, Map<String, Integer> strategy) {
        String[] split = sample.split("\n");
        int total = 0;
        for (String round : split) {
            total += strategy.get(round);
        }
        log.info("Total Score is {}", total);
    }
}

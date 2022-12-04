package adventofcode.aoc2022.day4;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class Day4Test {

    private static int total;

    @BeforeEach
    void setUp() {
        total = 0;
    }

    @Test
    public void inputPart1() {
        assertEquals(431, solve(Day4Input.INPUT, new FirstPartDay4Strategy()));
    }

    @Test
    public void samplePart1() {
        assertEquals(2, solve(Day4Input.SAMPLE, new FirstPartDay4Strategy()));
    }

    @Test
    public void inputPart2() {
        assertEquals(823, solve(Day4Input.INPUT, new SecondPartDay4Strategy()));
    }

    @Test
    public void samplePart2() {
        assertEquals(4, solve(Day4Input.SAMPLE, new SecondPartDay4Strategy()));
    }

    private static int solve(String input, Day4Strategy setOperation) {
        String[] pairs = input.split("\n");
        Arrays.stream(pairs).forEach(pair -> evaluatePair(setOperation, pair));
        return total;
    }

    private static void evaluatePair(Day4Strategy setOperation, String pair) {
        String[] sectionRanges = pair.split(",");
        int[][] boundaries = new int[2][2];
        IntStream.range(0, sectionRanges.length).forEach(i -> assignBoundaries(sectionRanges, boundaries, i));
        Set<Integer> firstElf = createSetOfSectionsForElf(boundaries[0]);
        Set<Integer> secondElf = createSetOfSectionsForElf(boundaries[1]);
        total = setOperation.applyStrategy(total, firstElf, secondElf);
    }

    private static Set<Integer> createSetOfSectionsForElf(int[] boundaries) {
        return IntStream.rangeClosed(boundaries[0], boundaries[1]).boxed().collect(Collectors.toSet());
    }

    private static void assignBoundaries(String[] sectionRanges, int[][] boundaries, int i) {
        String[] sections = sectionRanges[i].split("-");
        boundaries[i][0] = Integer.parseInt(sections[0]);
        boundaries[i][1] = Integer.parseInt(sections[1]);
    }

    private static class SecondPartDay4Strategy implements Day4Strategy {
        @Override
        public int applyStrategy(int total, Set<Integer> firstElf, Set<Integer> secondElf) {
            if (firstElf.stream().anyMatch(secondElf::contains)) total++;
            return total;
        }
    }

    private static class FirstPartDay4Strategy implements Day4Strategy {
        public int applyStrategy(int total, Set<Integer> firstElf, Set<Integer> secondElf) {
            if (firstElf.containsAll(secondElf) || secondElf.containsAll(firstElf)) total++;
            return total;
        }
    }
}

package adventofcode.aoc2022.day4;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class Day4Test {
    @Test
    public void inputPart1() {
        assertEquals(431, computePart1(Day4Input.INPUT, new FirstPartDay4Strategy()));
    }

    @Test
    public void samplePart1() {
        assertEquals(2, computePart1(Day4Input.SAMPLE, new FirstPartDay4Strategy()));
    }

    @Test
    public void inputPart2() {
        assertEquals(823, computePart1(Day4Input.INPUT, new SecondPartDay4Strategy()));
    }

    @Test
    public void samplePart2() {
        assertEquals(4, computePart1(Day4Input.SAMPLE, new SecondPartDay4Strategy()));
    }

    private static int computePart1(String input, Day4Strategy setOperation) {
        String[] pairs = input.split("\n");
        int total = 0;
        for (String pair : pairs) {
            String[] sectionRanges = pair.split(",");
            int elf = 0;
            int[][] boundaries = new int[2][2];
            for (String range : sectionRanges) {
                String[] sections = range.split("-");
                boundaries[elf][0] = Integer.valueOf(sections[0]);
                boundaries[elf++][1] = Integer.valueOf(sections[1]);
            }
            Set<Integer> firstElf = IntStream.rangeClosed(boundaries[0][0], boundaries[0][1]).boxed().collect(Collectors.toSet());
            Set<Integer> secondElf = IntStream.rangeClosed(boundaries[1][0], boundaries[1][1]).boxed().collect(Collectors.toSet());

            total = setOperation.applyStrategy(total, firstElf, secondElf);
        }
        return total;
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

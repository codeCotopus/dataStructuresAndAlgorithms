package adventofcode.aoc2022;

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
        assertEquals(431, computePart1(Day4Input.INPUT, new FirstPartComputer()));
    }

    @Test
    public void samplePart1() {
        assertEquals(2, computePart1(Day4Input.SAMPLE, new FirstPartComputer()));
    }

    @Test
    public void inputPart2() {
        assertEquals(823, computePart1(Day4Input.INPUT, new SecondPartComputer()));
    }

    @Test
    public void samplePart2() {
        assertEquals(4, computePart1(Day4Input.SAMPLE, new SecondPartComputer()));
    }

    private static int computePart1(String input, Computer setOperation) {
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

            total = setOperation.compute(total, firstElf, secondElf);
        }
        return total;
    }

    private static class SecondPartComputer implements Computer {
        @Override
        public int compute(int total, Set<Integer> firstElf, Set<Integer> secondElf) {
            for (Integer s : firstElf) {
                if (secondElf.contains(s)) {
                    total++;
                    break;
                }
            }
            return total;
        }
    }

    private static class FirstPartComputer implements Computer {
        public int compute(int total, Set<Integer> firstElf, Set<Integer> secondElf) {
            if (firstElf.containsAll(secondElf) || secondElf.containsAll(firstElf)) total++;
            return total;
        }
    }
}

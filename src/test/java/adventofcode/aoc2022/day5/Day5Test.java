package adventofcode.aoc2022.day5;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Slf4j
public class Day5Test {

    @Test
    public void part1Sample() {
        assertEquals("CMZ", solve(Day5Input.SAMPLE, new CraneCommandExecutionBehaviour1()));
    }

    @Test
    public void part2Sample() {
        assertEquals("MCD", solve(Day5Input.SAMPLE, new CraneCommandExecutionBehaviour2()));
    }

    @Test
    public void part1Input() {
        assertEquals("VRWBSFZWM", solve(Day5Input.INPUT, new CraneCommandExecutionBehaviour1()));
    }

    @Test
    public void part2Input() {
        assertEquals("RBTWJWMCF", solve(Day5Input.INPUT, new CraneCommandExecutionBehaviour2()));
    }

    private String solve(String input, CraneCommandExecutionBehaviour craneCommandExecutionBehaviour2) {
        String[] split = input.split("\n");

        Stack[] stacks = IntStream.range(0, getNumberOfStacks(split[0]))
                .mapToObj(i -> new Stack<>()).toArray(Stack[]::new);

        int i = 0;

        while (split[i].trim().charAt(0) != '1') {
            for (int j = 0; j < getNumberOfStacks(split[0]); j++) {
                if (getCrate(split[i], j).isPresent()) stacks[j].add(0, getCrate(split[i], j).get());
            }
            i++;
        }
        i += 2;

        int i1 = i;
        while (i1 < split.length) {
            Command command = new Command(split[i1++]);
            craneCommandExecutionBehaviour2.execute(stacks, command);

        }
        return Arrays.stream(stacks).map(s -> String.valueOf(s.peek())).collect(Collectors.joining());
    }

    @Test
    public void CanTakeCrate() {
        String s = "[A] [B]     [C]";
        assertEquals('A', getCrate(s, 0).get());
        assertEquals('B', getCrate(s, 1).get());
        assertEquals('C', getCrate(s, 3).get());
        assertFalse(getCrate(s, 2).isPresent());
        String s2 = "    [X]     [Y]";
        assertFalse(getCrate(s2, 0).isPresent());
        assertFalse(getCrate(s2, 2).isPresent());
        assertEquals('X', getCrate(s2, 1).get());
        assertEquals('Y', getCrate(s2, 3).get());
        assertEquals(4, getNumberOfStacks(s));
        assertEquals(1, getNumberOfStacks("   "));
        assertEquals(2, getNumberOfStacks("       "));
    }


    private int getNumberOfStacks(String s) {
        return s.length() / 4 + 1;
    }


    @Test
    public void CanGetMovements() {
        String line = "move 1 from 2 to 1";
        Command command = new Command(line);
        assertEquals(1, command.getQuantity());
        assertEquals(2, command.getFrom());
        assertEquals(1, command.getTo());
        String line2 = "move 3 from 4 to 5";
        Command command2 = new Command(line2);
        assertEquals(3, command2.getQuantity());
        assertEquals(4, command2.getFrom());
        assertEquals(5, command2.getTo());
    }

    private Optional<Character> getCrate(String s, int i) {
        int offset = 4 * i + 1;
        char value = s.charAt(offset);
        return value != ' ' ? Optional.of(value) : Optional.empty();
    }

    public static class Command {
        private final Matcher matcher;

        public Command(String line) {
            Pattern pattern = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
            matcher = pattern.matcher(line);
            matcher.find();
        }

        public Integer getQuantity() {

            return Integer.valueOf(matcher.group(1));
        }

        public int getFrom() {
            return Integer.parseInt(matcher.group(2));
        }

        public int getTo() {
            return Integer.parseInt(matcher.group(3));
        }
    }

    private static class CraneCommandExecutionBehaviour1 implements CraneCommandExecutionBehaviour {
        @Override
        public void execute(Stack<Character>[] stacks, Command command) {
            for (int j = 0; j < command.getQuantity(); j++) {
                Character pop = stacks[command.getFrom() - 1].pop();
                stacks[command.getTo() - 1].push(pop);
            }
        }
    }

    private static class CraneCommandExecutionBehaviour2 implements CraneCommandExecutionBehaviour {
        public void execute(Stack<Character>[] stacks, Command command) {
            Stack<Character> temp = new Stack<>();
            for (int j = 0; j < command.getQuantity(); j++) {
                Character pop = stacks[command.getFrom() - 1].pop();
                temp.push(pop);
            }
            for (int j = 0; j < command.getQuantity(); j++) {
                Character pop = temp.pop();
                stacks[command.getTo() - 1].push(pop);
            }
        }
    }
}

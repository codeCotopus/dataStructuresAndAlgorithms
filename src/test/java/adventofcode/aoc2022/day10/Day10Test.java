package adventofcode.aoc2022.day10;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class Day10Test {

    @Test
    public void part1Sample() {
        assertEquals(13140,part1(Day10Input.SAMPLE));
    }

    @Test
    public void part1Input() {
        assertEquals(14360,part1(Day10Input.INPUT));
    }

    private  int part1(String input) {
        String[] instructions = input.split("\n");

        Set<Integer> interestingCycles = Set.of(
                20, 60, 100, 140, 180, 220
        );

        List<Integer> cycles = new ArrayList<>();
        cycles.add(0);
        cycles.add(1);

        Arrays.stream(instructions).forEach((String instruction) -> processInstruction(cycles, instruction));

        for (int i = 1; i < cycles.size(); i++) {
            boolean pixelOn = isPixelOn((i-(i/40)*40) - 1, cycles.get(i));
            System.out.print(pixelOn ?"#":".");
            //log.info("pixel {} is on?:{}. registry: {}",i-1,isPixelOn(i-1,cycles.get(i)),cycles.get(i));
            if (i %40 ==0)System.out.print("\n");
        }

        return interestingCycles.stream()
                .mapToInt(interestingCycle -> interestingCycle * cycles.get(interestingCycle)).sum();
    }

    private void processInstruction(List<Integer> cycles, String instruction) {
        Integer previousRegistryValue = cycles.get(cycles.size() - 1);
        InstructionResult execute =
                Instruction.of(instruction).execute(cycles.size() - 1, previousRegistryValue);
        switch (execute.getCycleAfterExecution() - (cycles.size() - 1)) {
            case 2 -> cycles.add(previousRegistryValue);
        }
        cycles.add(execute.getRegisterXValueAfterExecution());


    }

    @Test
    public void pixelsShouldLitWhenTouchingValueOfX(){
        int x = 5;
        assertTrue(isPixelOn(4,x));
        assertTrue(isPixelOn(5,x));
        assertTrue(isPixelOn(6,x));
    }


    @Test
    public void pixelsShouldNotLitWhenNotTouchingValueOfX(){
        int x = 5;
        assertFalse(isPixelOn(3,x));
        assertFalse(isPixelOn(7,x));

    }
    private boolean isPixelOn(int pixel, int registry) {
        return IntStream.rangeClosed(registry - 1, registry + 1).anyMatch(x -> x == pixel);
    }


    @Test
    public void canDecodeInstructions() {
        InstructionResult result = Instruction.of("addx 3").execute(2, 1);
        assertEquals(4, result.getCycleAfterExecution());
        assertEquals(4, result.getRegisterXValueAfterExecution());

        InstructionResult result2 = Instruction.of("addx -5").execute(4, 4);
        assertEquals(6, result2.getCycleAfterExecution());
        assertEquals(-1, result2.getRegisterXValueAfterExecution());

        Instruction noop = Instruction.of("noop");
        InstructionResult execute = noop.execute(1, 1);

        assertEquals(2, execute.getCycleAfterExecution());
        assertEquals(1, execute.getRegisterXValueAfterExecution());

    }

}

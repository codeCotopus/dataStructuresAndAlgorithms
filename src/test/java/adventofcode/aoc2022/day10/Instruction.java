package adventofcode.aoc2022.day10;

import java.util.Optional;

public class Instruction {

    final String instruction;
    final String command;
    final Optional<String> value;

    public Instruction(String s) {
        instruction = s;
        command = s.substring(0, 4);
        value = s.length() > 4 ? Optional.of( s.substring(5)) : Optional.empty();
    }

    public static Instruction of(String instruction) {
        return new Instruction(instruction);
    }

    public InstructionResult execute(int cycle, int registerValue) {
        return switch (command) {
            case "noop" -> new InstructionResult(cycle + 1, registerValue);
            default -> new InstructionResult(cycle + 2, registerValue + Integer.valueOf(value.get()));
        };
    }
}

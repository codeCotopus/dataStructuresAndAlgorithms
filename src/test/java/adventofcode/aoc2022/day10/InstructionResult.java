package adventofcode.aoc2022.day10;

public class InstructionResult {
    private final int cycleAfterExecution;
    private final int registerXValueAfterExecution;

    public InstructionResult(int cycle, int registerX) {
        this.cycleAfterExecution = cycle;
        this.registerXValueAfterExecution = registerX;
    }

    public int getCycleAfterExecution() {
        return cycleAfterExecution;
    }

    public int getRegisterXValueAfterExecution() {

        return registerXValueAfterExecution;
    }
}

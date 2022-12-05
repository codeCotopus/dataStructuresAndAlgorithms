package adventofcode.aoc2022.day5;

import java.util.Stack;

public interface CraneCommandExecutionBehaviour {

    void execute(Stack<Character>[] stacks, Day5Test.Command command);
}

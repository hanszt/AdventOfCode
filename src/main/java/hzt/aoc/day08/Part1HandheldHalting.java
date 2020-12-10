package hzt.aoc.day08;

import java.util.List;

public class Part1HandheldHalting extends Day08Challenge {

    public Part1HandheldHalting() {
        super("Handheld Halting part 1",
                "Immediately before any instruction is executed a second time, " +
                        "what value is in the accumulator?");
    }


    @Override
    protected int solveByInstructions(List<Instruction> instructions) {
        return testInstructions(instructions).getGlobal();
    }

    public String getMessage(int global) {
        return String.format("The value of the global variable before second execution: %d%n", global);
    }

}

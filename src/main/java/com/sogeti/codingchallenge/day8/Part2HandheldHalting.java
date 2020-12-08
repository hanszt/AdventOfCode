package com.sogeti.codingchallenge.day8;

import java.util.List;

public class Part2HandheldHalting extends Day8Challenge {

    public Part2HandheldHalting() {
        super("Handheld Halting part 2",
                "Fix the program so that it terminates normally by changing exactly one jmp (to nop) or nop (to jmp). " +
                        "What is the value of the accumulator after the program terminates?");
    }

    @Override
    protected int solveByInstructions(List<Instruction> instructions) {
        for (Instruction instruction : instructions) {
            instructions.forEach(item -> item.visited = false);
            swapJumpAndNoOperation(instruction);
            Result result = testInstructions(instructions);
            if (result.lastInstruction.nr == instructions.size()) return result.global;
            swapJumpAndNoOperation(instruction);
        }
        return 0;
    }

    private void swapJumpAndNoOperation(Instruction instruction) {
        if (instruction.descriptor.equals(NO_OPERATION)) instruction.descriptor = JUMP;
        else if (instruction.descriptor.equals(JUMP)) instruction.descriptor = NO_OPERATION;
    }

    @Override
    public String getMessage(int global) {
        return String.format("The value of the global variable after correct termination: %d", global);
    }
}

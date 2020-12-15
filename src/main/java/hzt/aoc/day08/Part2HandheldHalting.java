package hzt.aoc.day08;

import java.util.List;

public class Part2HandheldHalting extends Day08Challenge {

    public Part2HandheldHalting() {
        super("part 2",
                "Fix the program so that it terminates normally by changing exactly one jmp (to nop) or nop (to jmp). " +
                        "What is the value of the accumulator after the program terminates?");
    }

    @Override
    protected int solveByInstructions(List<Instruction> instructions) {
        for (Instruction instruction : instructions) {
            instructions.forEach(item -> item.setVisited(false));
            swapJumpAndNoOperation(instruction);
            Result result = testInstructions(instructions);
            if (result.getLastInstruction().getNr() == instructions.size()) return result.getGlobal();
            swapJumpAndNoOperation(instruction);
        }
        return 0;
    }

    private void swapJumpAndNoOperation(Instruction instruction) {
        if (instruction.getDescriptor().equals(NO_OPERATION)) instruction.setDescriptor(JUMP);
        else if (instruction.getDescriptor().equals(JUMP)) instruction.setDescriptor(NO_OPERATION);
    }

    @Override
    public String getMessage(int global) {
        return String.format("The value of the global variable after correct termination: %d%n", global);
    }
}

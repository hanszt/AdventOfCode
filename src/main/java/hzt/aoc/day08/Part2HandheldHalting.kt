package hzt.aoc.day08

import java.util.function.Consumer

class Part2HandheldHalting : Day08Challenge(
    "part 2",
    "Fix the program so that it terminates normally by changing exactly one jmp (to nop) or nop (to jmp). " +
            "What is the value of the accumulator after the program terminates"
) {
    override fun solveByInstructions(instructions: List<Instruction>): Int {
        for (instruction in instructions) {
            instructions.forEach(Consumer { it.isVisited = false })
            swapJumpAndNoOperation(instruction)
            val result = testInstructions(instructions)
            if (result.lastInstruction?.nr == instructions.size) return result.global
            swapJumpAndNoOperation(instruction)
        }
        return 0
    }

    private fun swapJumpAndNoOperation(instruction: Instruction) {
        if (instruction.descriptor == NO_OPERATION) instruction.descriptor = JUMP
        else if (instruction.descriptor == JUMP) instruction.descriptor = NO_OPERATION
    }

    override fun getMessage(result: String): String {
        return String.format("The value of the global variable after correct termination: %s%n", result)
    }
}

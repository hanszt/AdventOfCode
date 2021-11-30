package hzt.aoc.day08

class Part1HandheldHalting : Day08Challenge(
    "part 1",
    "Immediately before any instruction is executed a second time, " +
            "what value is in the accumulator"
) {
    override fun solveByInstructions(instructions: List<Instruction>): Int {
        return testInstructions(instructions).global
    }

    override fun getMessage(result: String): String {
        return String.format("The value of the global variable before second execution: %s%n", result)
    }
}

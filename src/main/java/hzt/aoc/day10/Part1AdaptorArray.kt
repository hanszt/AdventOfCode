package hzt.aoc.day10

class Part1AdaptorArray : Day10Challenge(
    "part 1",
    "Find a chain that uses all of your adapters to connect the charging outlet to " +
            "your device's built-in adapter and count the joltage differences between the charging outlet, " +
            "the adapters, and your device. " +
            "What is the number of 1-jolt differences multiplied by the number of 3-jolt differences"
) {
    override fun solveByList(list: List<Int>): Number {
        return calculateTheProductBetweenOneAndThreeDifference(list)
    }

    override fun getMessage(result: String): String {
        return String.format(
            "The number of 1-jolt differences multiplied by the number of 3-jolt differences is: %s",
            result
        )
    }
}

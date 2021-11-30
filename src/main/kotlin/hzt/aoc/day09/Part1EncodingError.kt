package hzt.aoc.day09

class Part1EncodingError : Day09Challenge(
    "part 1",
    "The first step of attacking the weakness in the XMAS data is to find the first number in the list " +
            "(after the preamble) which is not the sum of two of the 25 numbers before it." +
            "What is the first number that does not have this property"
) {
    override fun solveByXmasList(longs: List<Long>): Long {
        return findFirstNumberNotSumOfTwoIntegersInPreamble(longs)
    }

    override fun getMessage(result: String): String {
        return String.format("The first number that is not the sum of two of the 25 numbers before it is: %s%n", result)
    }
}

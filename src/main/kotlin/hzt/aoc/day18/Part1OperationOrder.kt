package hzt.aoc.day18

class Part1OperationOrder : Day18Challenge(
    "part 1",
    "Evaluate the expression on each line of the homework; what is the sum of the resulting values"
) {
    override fun evaluateBetweenParentheses(strings: List<String>): String {
        return evaluateInOrder(strings)
    }

    override fun getMessage(value: Long): String {
        return String.format("%d", value)
    }
}

package hzt.aoc.day18

class Part2OperationOrder : Day18Challenge(
    "part 2",
    "What do you get if you add up the results of evaluating the homework problems using these new rules"
) {
    override fun evaluateBetweenParentheses(strings: List<String>): String {
        var elementList = strings
        var subResult = "0"
        var newList: List<String>
        while (elementList.contains(OPERATOR_TO_EVALUATE_FIRST)) {
            newList = ArrayList(elementList)
            for (i in elementList.indices) {
                if (elementList[i] == OPERATOR_TO_EVALUATE_FIRST) {
                    subResult = parseAndCalculateSubResult(elementList, i)
                    replaceEquationBySubResult(newList, subResult, i - 1, EVALUATION_LENGTH)
                    break
                }
            }
            elementList = newList
            LOGGER.trace(elementList)
        }
        if (elementList.size > 1) subResult = evaluateInOrder(elementList)
        LOGGER.trace("Sub result part 2: $subResult")
        return subResult
    }

    private fun parseAndCalculateSubResult(elementList: List<String>, index: Int): String {
        val first = elementList[index - 1].toLong()
        val second = elementList[index + 1].toLong()
        val longSubResult = evaluate(first, OPERATOR_TO_EVALUATE_FIRST, second)
        return longSubResult.toString()
    }

    override fun getMessage(value: Long): String {
        return String.format("%d", value)
    }

    companion object {
        private const val EVALUATION_LENGTH = 3
        private const val OPERATOR_TO_EVALUATE_FIRST = "+"
    }
}

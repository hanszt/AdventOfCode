package hzt.aoc.day10

import java.math.BigInteger

class Part2AdaptorArrayWithoutCaching : Day10Challenge(
    "part 2 without caching",
    "What is the total number of distinct ways " +
            "you can arrange the adapters to connect the charging outlet to your device",
    "20201210-input-day10ref2.txt"
) {
    override fun solveByList(list: List<Int>): Number {
        return numberOfWaysToCompleteAdaptorChain(list)
    }

    private fun numberOfWaysToCompleteAdaptorChain(sortedList: List<Int>): BigInteger {
        if (sortedList.size == 1) return BigInteger.ONE
        var arrangements = BigInteger.ZERO
        var index = 1
        val current = sortedList[0] // first index in sorted list
        while (sortedList.size > index && sortedList[index] - current <= MAX_STEP_APART) {
            val subArrangements = numberOfWaysToCompleteAdaptorChain(sortedList.subList(index, sortedList.size))
            arrangements = arrangements.add(subArrangements)
            index++
        }
        return arrangements
    }

    override fun getMessage(number: String): String {
        return String.format(
            "The number of distinct ways to connect your adaptor is (Only works for small input set): %s%n",
            number
        )
    }
}

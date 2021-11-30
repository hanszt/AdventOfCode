package hzt.aoc.day10

import java.util.Arrays
import java.util.HashMap
import java.math.BigInteger

// credits to TurkeyDev
class Part2AdaptorArrayWithCaching : Day10Challenge(
    "part 2 with caching using bigInteger",
    "What is the total number of distinct ways " +
            "you can arrange the adapters to connect the charging outlet to your device"
) {
    override fun solveByList(list: List<Int>): Number {
        return numberOfWaysToCompleteAdaptorChain(list)
    }

    //improves runtime: Allows to skip parts of the branches in the tree to be recursively walk through.,,
    private val cache: MutableMap<String, BigInteger> = HashMap()
    private fun numberOfWaysToCompleteAdaptorChain(sortedList: List<Int>): BigInteger {
        if (sortedList.size == 1) return BigInteger.ONE
        var arrangements = BigInteger.ZERO
        var index = 1
        val current = sortedList[0]
        while (sortedList.size > index && sortedList[index] - current <= MAX_STEP_APART) {
            val subList = sortedList.subList(index, sortedList.size)
            val stringSubList = Arrays.toString(subList.toTypedArray())
            if (!cache.containsKey(stringSubList)) {
                val subArrangements = numberOfWaysToCompleteAdaptorChain(subList)
                cache[stringSubList] = subArrangements
                arrangements = arrangements.add(subArrangements)
            } else arrangements = arrangements.add(cache[stringSubList])
            index++
        }
        return arrangements
    }

    override fun getMessage(result: String): String {
        return String.format("The number of distinct ways to connect your adaptor is: %s%n", result)
    }
}

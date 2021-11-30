package hzt.aoc.day10

// credits to TurkeyDev
class Part2AdaptorArrayWithCachingLongs : Day10Challenge(
    "part 2 with caching using longs",
    "What is the total number of distinct ways " +
            "you can arrange the adapters to connect the charging outlet to your device"
) {
    override fun solveByList(list: List<Int>): Number {
        return numberOfWaysToCompleteAdaptorChain(list)
    }

    //improves runtime: Allows to skip parts of the branches in the tree to be recursively walk through.,,
    private val cache: MutableMap<String, Long> = HashMap()
    private fun numberOfWaysToCompleteAdaptorChain(sortedList: List<Int>): Long {
        if (sortedList.size == 1) return 1
        var arrangements: Long = 0
        var index = 1
        val current = sortedList[0]
        while (sortedList.size > index && sortedList[index] - current <= MAX_STEP_APART) {
            val subList = sortedList.subList(index, sortedList.size)
            val stringSubList = subList.toTypedArray().contentToString()
            if (!cache.containsKey(stringSubList)) {
                val subArrangements = numberOfWaysToCompleteAdaptorChain(subList)
                cache[stringSubList] = subArrangements
                arrangements += subArrangements
            } else arrangements += cache[stringSubList] ?: 0
            index++
        }
        return arrangements
    }

    override fun getMessage(result: String): String =
        String.format("The number of distinct ways to connect your adaptor is: %s%n", result)
}

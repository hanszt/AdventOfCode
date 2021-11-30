package hzt.aoc.day15

import java.util.HashMap
import java.lang.System

// Credits to TurkeyDev
class Part2RambunctiousRecitation : Day15Challenge(
    "part 2",
    "Given your starting numbers, what will be the 30000000th number spoken"
) {
    override fun getNthNumberSpoken(numbers: MutableList<Int>): Int {
        var start = System.nanoTime()
        var index = 0
        var last = -1
        val seenLastToIndex: MutableMap<Int, Int> = HashMap()
        for (i in numbers.indices) {
            index++
            last = numbers[i]
            if (i != numbers.size - 1) seenLastToIndex[last] = index
        }
        while (index < THRESHOLD) {
            val seenLastTemp = seenLastToIndex.getOrDefault(last, -1)
            seenLastToIndex[last] = index
            last = if (seenLastTemp == -1) 0 else index - seenLastTemp
            index++
            start = logTime(index, LOG_STEP, 0, last, start)
        }
        return last
    }

    override fun getMessage(global: String): String {
        return String.format("The %dth number spoken is: %s", THRESHOLD, global)
    }

    companion object {
        private const val THRESHOLD = 30000000
        private const val LOG_STEP = 3000000
    }
}

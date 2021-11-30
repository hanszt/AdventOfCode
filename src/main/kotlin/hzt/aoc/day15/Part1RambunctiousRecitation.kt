package hzt.aoc.day15

import java.lang.System

class Part1RambunctiousRecitation : Day15Challenge(
    "part 1",
    "Given your starting numbers, what will be the 2020th number spoken"
) {
    override fun getNthNumberSpoken(numbers: MutableList<Int>): Int {
        var last = 0
        var start = System.nanoTime()
        while (numbers.size < THRESHOLD) {
            val prevLast = numbers.removeAt(numbers.size - 1)
            var newLast = 0
            if (numbers.contains(prevLast)) {
                for (index in numbers.indices.reversed()) {
                    if (numbers[index] == prevLast) {
                        newLast = numbers.size - index
                        break
                    }
                }
            }
            numbers.add(prevLast)
            last = newLast
            numbers.add(last)
            start = logTime(numbers.size, 200, 20, last, start)
        }
        return last
    }

    override fun getMessage(result: String): String {
        return String.format("The %dth number spoken is: %s", THRESHOLD, result)
    }

    companion object {
        private const val THRESHOLD = 2020
    }
}

package hzt.aoc.day09

import java.util.ArrayList

class Part2EncodingError : Day09Challenge(
    "part 2",
    "you must find a contiguous set of at least two numbers " +
            "in your list which sum to the invalid number from step 1. " +
            "To find the encryption weakness, add together the smallest and largest number in this contiguous range"
) {
    override fun solveByXmasList(longs: List<Long>): Long {
        val resultStep1 = findFirstNumberNotSumOfTwoIntegersInPreamble(longs)
        val contiguousSumList = findSumList(longs, resultStep1)
        return findSumMinAndMaxNumber(contiguousSumList)
    }

    private fun findSumList(longs: List<Long>, ref: Long): List<Long> {
        var sum: Long = 0
        val sumList: MutableList<Long> = ArrayList()
        for (i in longs.indices) {
            for (j in i until longs.size) {
                sumList.add(longs[j])
                sum += longs[j]
                if (sum == ref) return sumList
            }
            sumList.clear()
            sum = 0
        }
        return sumList
    }

    private fun findSumMinAndMaxNumber(list: List<Long>): Long {
        val min = list.minOf { it }
        val max = list.maxOf { it }
        return min + max
    }

    override fun getMessage(result: String): String {
        return String.format("%s%n", result)
    }
}

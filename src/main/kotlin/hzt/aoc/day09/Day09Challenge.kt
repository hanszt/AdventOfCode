package hzt.aoc.day09

import hzt.aoc.Challenge
import java.util.stream.Collectors

abstract class Day09Challenge internal constructor(challengeTitle: String, description: String) :
    Challenge(challengeTitle, description, "20201209-input-day9.txt") {
    override fun solve(inputList: List<String>): String {
        val numbers = inputList.stream().map { s: String -> s.toLong() }.collect(Collectors.toList())
        return solveByXmasList(numbers).toString()
    }

    protected abstract fun solveByXmasList(longs: List<Long>): Long
    fun findFirstNumberNotSumOfTwoIntegersInPreamble(longs: List<Long>): Long {
        var result: Long = 0
        val preambleNumbers: MutableList<Long> = ArrayList()
        for (i in longs.indices) {
            if (preambleNumbers.size < PRE_AMBLE_LENGTH) preambleNumbers.add(longs[i]) else if (preambleNumbers.size == PRE_AMBLE_LENGTH) {
                val isSum = sumOfUniquePairInPreamble(longs[i], preambleNumbers)
                if (!isSum) {
                    result = longs[i]
                    break
                }
                preambleNumbers.remove(longs[i - PRE_AMBLE_LENGTH])
                preambleNumbers.add(longs[i])
            }
        }
        return result
    }

    private fun sumOfUniquePairInPreamble(current: Long, preAmbleNumbers: List<Long>): Boolean {
        for (number in preAmbleNumbers) {
            val difference = current - number
            if (preAmbleNumbers.contains(difference) && difference != number) return true
        }
        return false
    }

    companion object {
        const val PRE_AMBLE_LENGTH = 25
    }
}

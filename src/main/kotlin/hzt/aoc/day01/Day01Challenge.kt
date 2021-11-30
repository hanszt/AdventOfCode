package hzt.aoc.day01

import hzt.aoc.Challenge
import java.util.*

abstract class Day01Challenge protected constructor(challenge: String, description: String) :
    Challenge(challenge, description, "20201201-input-day1.txt") {
    private val integersThatSumTo2020List: MutableList<Array<Int>> = ArrayList()

    override fun solve(inputList: List<String>): String {
        val integers = inputList.map(String::toInt).toSet()
        integersThatSumTo2020List.clear()
        integersThatSumTo2020List.addAll(findIntegersListThatSumTo2020(TreeSet(integers)))
        LOGGER.trace(getMessage(integersThatSumTo2020List))
        return calculateProduct(integersThatSumTo2020List[0]).toString()
    }

    protected abstract fun findIntegersListThatSumTo2020(integers: SortedSet<Int>): List<Array<Int>>
    fun getMessage(integersThatSumTo2020List: List<Array<Int>>): String {
        val sb = StringBuilder()
        val message = String.format("Output size: %d%n", integersThatSumTo2020List.size)
        sb.append(message)
        for (entries in integersThatSumTo2020List) {
            val isb = StringBuilder()
            var product: Long = 1
            for (integer in entries) {
                isb.append(integer).append(", ")
                product *= integer.toLong()
            }
            val result = String.format(
                "The %d digits from the list that sum to %d are: %s%nThe product of these digits is: %d%n",
                entries.size, SUM_TO_BE_FOUND, isb, product
            )
            sb.append(result)
        }
        return sb.toString()
    }

    private fun calculateProduct(entries: Array<Int>): Long {
        var product: Long = 1
        for (integer in entries) {
            product *= integer.toLong()
        }
        return product
    }

    override fun getMessage(result: String): String = getMessage(integersThatSumTo2020List)

    companion object {
        const val SUM_TO_BE_FOUND = 2020
    }
}

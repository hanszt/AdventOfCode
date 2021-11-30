package hzt.aoc.day01

import java.util.*

class Part1ReportRepair : Day01Challenge(
    "part 1",
    "Find the product of the two entries that sum to 2020"
) {
    override fun findIntegersListThatSumTo2020(integers: SortedSet<Int>): List<Array<Int>> {
        val entriesList: MutableList<Array<Int>> = ArrayList()
        for (integer in integers) {
            val difference: Int = SUM_TO_BE_FOUND - integer
            if (integers.contains(difference)) {
                val entries = arrayOf(integer, difference)
                entriesList.add(entries)
                break
            }
        }
        return entriesList
    }
}

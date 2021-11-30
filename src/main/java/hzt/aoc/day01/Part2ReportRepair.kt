package hzt.aoc.day01

import java.util.*

class Part2ReportRepair : Day01Challenge("part 2", "Find the product of the three digits that sum to 2020") {
    override fun findIntegersListThatSumTo2020(integers: SortedSet<Int>): List<Array<Int>> {
        val usedIntegers: MutableSet<Int> = HashSet()
        val integerList: List<Int> = ArrayList(integers)
        val entriesList: MutableList<Array<Int>> = ArrayList()
        for (i in integerList.indices) {
            for (j in i + 1 until integerList.size) {
                val difference: Int = SUM_TO_BE_FOUND - integerList[i] - integerList[j]
                if (!usedIntegers.contains(integerList[i]) && !usedIntegers.contains(integerList[j])
                    && integers.contains(difference)
                ) {
                    val threeEntries = arrayOf(integerList[i], integerList[j], difference)
                    entriesList.add(threeEntries)
                    usedIntegers.add(integerList[i])
                    usedIntegers.add(integerList[j])
                    break
                }
            }
        }
        return entriesList
    }
}

package hzt.aoc.day13

import java.math.BigInteger
import java.util.*

class Part2ShuttleSearchOwnImpl : Day13Challenge(
    "part 2",
    "What is the earliest timestamp such that all of the listed bus IDs depart at " +
            "offsets matching their positions in the list (This is a brute force implementation. Is too slow)",
    "20201213-input-day13ref2.txt"
) {
    override fun solve(inputList: List<String>): String {
        val busNrList = listOf(*inputList[1].split(",".toRegex()).toTypedArray())
        val highestIndex = busNrList.size - 1
        val listPositionsToBusNrs: MutableMap<Int, Int> = TreeMap()
        for (i in busNrList.indices) {
            if (busNrList[i] != "x") {
                listPositionsToBusNrs[i] = busNrList[i].toInt()
            }
        }
        var earliestTimestamp = BigInteger.ZERO
        while (true) {
            val matches: MutableList<Boolean> = ArrayList()
            for ((listPosition, busNr) in listPositionsToBusNrs) {
                val value = earliestTimestamp.mod(BigInteger.valueOf(busNr.toLong()))
                    .add(BigInteger.valueOf(listPosition.toLong() - highestIndex))
                matches.add(value == BigInteger.ZERO)
            }
            if (!matches.contains(false)) break
            earliestTimestamp = earliestTimestamp.add(BigInteger.ONE)
        }
        return earliestTimestamp.subtract(BigInteger.valueOf(highestIndex.toLong())).toString()
    }

    override fun getMessage(result: String): String {
        return String.format("%s (only works for small inputs)", result)
    }
}

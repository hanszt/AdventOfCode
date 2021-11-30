package hzt.aoc.day13

import java.util.*
import java.util.stream.Collectors

class Part1ShuttleSearch : Day13Challenge(
    "part 1",
    "What is the ID of the earliest bus you can take to the airport " +
            "multiplied by the number of minutes you'll need to wait for that bus"
) {
    override fun solve(inputList: List<String>): String {
        val earliestTimestamp = inputList[0].toInt()
        val integers = Arrays.stream(inputList[1].split(",".toRegex()).toTypedArray())
            .filter { it != "x" }
            .map(String::toInt).collect(Collectors.toList())
        val busNumbersToWaitingTimes = integers.stream()
            .collect(Collectors.toMap(
                    { busNr -> busNr },
                    { busNr -> busNr - earliestTimestamp % busNr })
            )
        var timeToWaitForEarliestBus = Int.MAX_VALUE
        var busNumberBelongingToSmallest = 0
        for ((key, value) in busNumbersToWaitingTimes) {
            if (value < timeToWaitForEarliestBus) {
                timeToWaitForEarliestBus = value
                busNumberBelongingToSmallest = key
            }
        }
        return (timeToWaitForEarliestBus * busNumberBelongingToSmallest).toString()
    }
}

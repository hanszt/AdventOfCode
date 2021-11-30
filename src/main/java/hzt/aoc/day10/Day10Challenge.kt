package hzt.aoc.day10

import hzt.aoc.Challenge
import java.util.stream.Collectors

abstract class Day10Challenge : Challenge {
    internal constructor(challengeTitle: String, description: String) : super(
        challengeTitle,
        description,
        "20201210-input-day10.txt"
    )

    internal constructor(challengeTitle: String, description: String, inputFilename: String) : super(
        challengeTitle,
        description,
        inputFilename
    )

    override fun solve(inputList: List<String>): String {
        val list = inputList.stream().filter { s: String -> !s.isEmpty() }.map { s: String -> s.toInt() }
            .sorted().collect(Collectors.toList())
        list.add(0, 0) // add socket jolt value
        list.add(list[list.size - 1] + MAX_STEP_APART) // add built in phone adaptor jolt value
        return solveByList(list).toString()
    }

    protected abstract fun solveByList(list: List<Int>): Number
    fun calculateTheProductBetweenOneAndThreeDifference(sortedlist: List<Int>): Long {
        var oneDifference: Long = 0
        var threeDifference: Long = 0
        for (i in 0 until sortedlist.size - 1) {
            val difference = sortedlist[i + 1] - sortedlist[i]
            if (difference == 1) oneDifference++
            if (difference == MAX_STEP_APART) threeDifference++
        }
        return oneDifference * threeDifference
    }

    companion object {
        const val MAX_STEP_APART = 3
    }
}

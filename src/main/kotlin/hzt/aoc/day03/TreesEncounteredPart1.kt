package hzt.aoc.day03

import java.awt.Point

class TreesEncounteredPart1 : Day03Challenge(
    "part 1",
    "Find the number of trees crossed"
) {
    override fun calculateResult(grid: List<List<Boolean>>): Long {
        return calculateNumberOfTreesEncountered(grid, Point(0, 0), Path.SLOPE3_1.slope).toLong()
    }

    override fun getMessage(result: String): String {
        return String.format("The number of trees crossed is: %s%n", result)
    }
}

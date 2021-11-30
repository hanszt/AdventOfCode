package hzt.aoc.day03

import hzt.aoc.Challenge
import java.awt.Point

abstract class Day03Challenge protected constructor(part: String, description: String) :
    Challenge(part, description, "20201203-input-day3.txt") {
    override fun solve(inputList: List<String>): String {
        val grid = if (!inputList.isEmpty()) buildGrid(inputList) else emptyList<List<Boolean>>()
        LOGGER.trace(booleanGrid2DAsString(grid))
        return calculateResult(grid).toString()
    }

    protected abstract fun calculateResult(grid: List<List<Boolean>>): Long
    fun calculateNumberOfTreesEncountered(treeGrid: List<List<Boolean>>, position: Point, slope: Point): Int {
        var numberOfTrees = 0
        while (position.getY() < treeGrid.size) {
            LOGGER.trace("x: " + position.x + ", y: " + position.y + ", Is tree: " + treeGrid[position.y][position.x])
            val isTree = treeGrid[position.y][position.x]
            if (isTree) numberOfTrees++
            position.translate(slope.x, slope.y)
        }
        return numberOfTrees
    }

    private fun buildGrid(inputList: List<String>): List<List<Boolean>> {
        val patternLength = inputList[0].length
        val height = inputList.size
        val length = height * Path.SLOPE7_1.slope.getX()
        val timesRepeatedHorizontally = Math.round(length / patternLength).toInt()
        val gird: MutableList<List<Boolean>> = ArrayList()
        for (patternRow in inputList) {
            val newRow: MutableList<Boolean> = ArrayList()
            val newRowArray = patternRow.repeat(timesRepeatedHorizontally).toCharArray()
            for (c in newRowArray) {
                newRow.add(c == TREE)
            }
            gird.add(newRow)
        }
        return gird
    }

    internal enum class Path(val slope: Point) {
        SLOPE3_1(Point(3, 1)), SLOPE1_1(Point(1, 1)), SLOPE5_1(Point(5, 1)), SLOPE7_1(Point(7, 1)), SLOPE1_2(
            Point(
                1,
                2
            )
        );

    }

    companion object {
        private const val TREE = '#'
    }
}

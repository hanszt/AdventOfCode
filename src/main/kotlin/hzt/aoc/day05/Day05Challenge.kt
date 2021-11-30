package hzt.aoc.day05

import hzt.aoc.Challenge
import hzt.aoc.day05.model.Seat

abstract class Day05Challenge protected constructor(challengeTitle: String, description: String) :
    Challenge(challengeTitle, description, "20201205-input-day5.txt") {

    override fun solve(inputList: List<String>): String {
        val seats = inputList.map(::toSeat)
        val result = calculateResult(seats)
        return result.toString()
    }

    protected abstract fun calculateResult(seats: List<Seat>): Int

    fun toSeat(string: String): Seat {
        var lowerBoundRows = 0
        var upperBoundRows = NUMBER_OF_ROWS
        var lowerBoundCols = 0
        var upperBoundCols = NUMBER_OF_COLUMNS
        for (i in 0 until string.length) {
            if (i < AMOUNT_SIGNS_FRONT_BACK) {
                if (string[i] == KEEP_UPPER_HALF_ROWS) {
                    lowerBoundRows = newLowerBound(lowerBoundRows, upperBoundRows)
                } else if (string[i] == KEEP_LOWER_HALF_ROWS) {
                    upperBoundRows = newUpperBound(lowerBoundRows, upperBoundRows)
                }
            } else {
                if (string[i] == KEEP_UPPER_HALF_COLS) {
                    lowerBoundCols = newLowerBound(lowerBoundCols, upperBoundCols)
                } else if (string[i] == KEEP_LOWER_HALF_COLS) {
                    upperBoundCols = newUpperBound(lowerBoundCols, upperBoundCols)
                }
            }
        }
        return Seat(string, lowerBoundRows, lowerBoundCols)
    }

    private fun newLowerBound(lower: Int, upper: Int): Int = lower + (upper - lower) / 2

    private fun newUpperBound(lower: Int, upper: Int): Int = upper - (upper - lower) / 2

    fun findHighestSeatID(boardingPassIds: List<Int>): Int = boardingPassIds.maxOf { it }

    companion object {
        const val NUMBER_OF_ROWS = 128
        const val NUMBER_OF_COLUMNS = 8
        const val KEEP_LOWER_HALF_ROWS = 'F'
        const val KEEP_UPPER_HALF_ROWS = 'B'
        const val KEEP_LOWER_HALF_COLS = 'L'
        const val KEEP_UPPER_HALF_COLS = 'R'
        const val AMOUNT_SIGNS_FRONT_BACK = 7
    }
}

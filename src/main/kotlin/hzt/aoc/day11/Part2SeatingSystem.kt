package hzt.aoc.day11

import java.util.function.IntBinaryOperator

// Credits to Johan de Jong
class Part2SeatingSystem : Day11Challenge(
    "part 2",
    "Now, instead of considering just the eight immediately adjacent seats, " +
            "consider the first seat in each of those eight directions. " +
            "For example, the empty seat below would see eight occupied seats. echter " +
            "Given the new visibility method and the rule change for occupied seats becoming empty, " +
            "once equilibrium is reached, how many seats end up occupied",
    "20201211-input-day11.txt"
) {
    private lateinit var state: Array<CharArray>
    private var width = 0
    private var height = 0

    override fun solve(inputList: List<String>): String {
        width = inputList[0].length
        height = inputList.size
        state = Array(height) { CharArray(width) }
        for (y in 0 until height) {
            val s = inputList[y]
            for (x in 0 until width) {
                state[y][x] = s[x]
            }
        }
        return iterate(this::adjacentOccupiedLine).toString()
    }

    private fun iterate(adjacentOccupiedFunction: IntBinaryOperator): Int {
        var updated = true
        while (updated) {
            updated = performUpdate(adjacentOccupiedFunction)
        }
        return countOccupied()
    }

    private fun performUpdate(adjacentOccupiedFunction: IntBinaryOperator): Boolean {
        var updated = false
        val nextState = Array(height) { CharArray(width) }
        for (row in 0 until height) {
            for (col in 0 until width) {
                if (state[row][col] == EMPTY_SEAT && adjacentOccupiedFunction.applyAsInt(col, row) == 0) {
                    nextState[row][col] = OCCUPIED_SEAT
                    updated = true
                } else if (state[row][col] == OCCUPIED_SEAT &&
                    adjacentOccupiedFunction.applyAsInt(col, row) >= THRESHOLD_BECOMES_EMPTY
                ) {
                    nextState[row][col] = EMPTY_SEAT
                    updated = true
                } else {
                    nextState[row][col] = state[row][col]
                }
            }
        }
        state = nextState
        return updated
    }

    private fun adjacentOccupiedLine(x: Int, y: Int): Int {
        var result = 0
        for (dy in -1..1) {
            for (dx in -1..1) {
                if ((dx != 0 || dy != 0) && adjacentOccupiedLine(x, y, dx, dy)) {
                    result++
                }
            }
        }
        return result
    }

    private fun adjacentOccupiedLine(x: Int, y: Int, dx: Int, dy: Int): Boolean {
        var mx = x
        var my = y
        while (true) {
            mx += dx
            my += dy
            val c = get(mx, my)
            if (c == OCCUPIED_SEAT) return true
            if (c == EMPTY_SEAT || c == '\u0000') return false
        }
    }

    private fun countOccupied(): Int {
        var result = 0
        for (y in 0 until height) {
            for (x in 0 until width) {
                if (state[y][x] == OCCUPIED_SEAT) {
                    result++
                }
            }
        }
        return result
    }

    private operator fun get(x: Int, y: Int): Char {
        return if (x < 0 || y < 0 || x >= width || y >= height) {
            '\u0000'
        } else state[y][x]
    }

    override fun getMessage(result: String): String {
        return String.format("The number of seats occupied after equilibrium: %s%n", result)
    }

    companion object {
        private const val THRESHOLD_BECOMES_EMPTY = 5
    }
}

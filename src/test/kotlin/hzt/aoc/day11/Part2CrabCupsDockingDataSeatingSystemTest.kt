package hzt.aoc.day11

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Part2CrabCupsDockingDataSeatingSystemTest {

    @Test
    fun isOccupiedAfterUpdate() {
            val row = 3
            val col = 3
            val grid = arrayOf(
                charArrayOf('L', 'L', 'L', 'L'),
                charArrayOf('L', 'L', 'L', 'L'),
                charArrayOf('L', 'L', '.', 'L'),
                charArrayOf('L', '.', 'L', 'L')
            )
            val occupied = occupiedSeatsInLineOfSight(grid, row, col)
            println()
            for (array in grid) {
                for (c in array) print(c)
                println()
            }
            Assertions.assertEquals(0, occupied)
        }

    @Test
    fun isOccupiedAfterUpdate1() {
            val row = 3
            val col = 2
            val grid = arrayOf(
                charArrayOf('#', '#', '#', '#'),
                charArrayOf('#', '#', '#', '#'),
                charArrayOf('#', '#', '.', '#'),
                charArrayOf('#', '.', '#', '#')
            )
            val occupied = occupiedSeatsInLineOfSight(grid, row, col)
            println()
            for (array in grid) {
                for (c in array) print(c)
                println()
            }
            Assertions.assertEquals(5, occupied)
        }

    @Test
    fun isOccupiedAfterUpdate3() {
            val row = 3
            val col = 2
            val grid = arrayOf(
                charArrayOf('#', '#', '#', '#'),
                charArrayOf('#', '#', '#', '#'),
                charArrayOf('#', '#', '.', '#'),
                charArrayOf('#', '.', '#', '#')
            )
            val occupied = occupiedSeatsInLineOfSight(grid, row, col)
            println()
            for (array in grid) {
                for (c in array) print(c)
                println()
            }
            Assertions.assertEquals(5, occupied)
        }

    @Test
    fun isOccupiedAfterUpdate2() {
            val row = 2
            val col = 2
            val grid = arrayOf(
                charArrayOf('#', '#', '#', '#'),
                charArrayOf('#', '#', '#', '#'),
                charArrayOf('#', '#', '.', '#'),
                charArrayOf('#', '.', '#', '#')
            )
            val occupied = occupiedSeatsInLineOfSight(grid, row, col)
            println()
            for (array in grid) {
                for (c in array) print(c)
                println()
            }
            Assertions.assertEquals(7, occupied)
        }

    @Test
    fun isOccupiedAfterUpdate4() {
            val occupiedValues: MutableList<Int> = ArrayList()
            val grid = arrayOf(
                charArrayOf('#', '.', '#', '#', '#'),
                charArrayOf('.', '.', '#', '.', '#'),
                charArrayOf('#', '#', '#', '#', '#'),
                charArrayOf('#', '.', '#', '#', '#'),
                charArrayOf('#', '.', '#', '#', '#'),
                charArrayOf('#', '.', '#', '#', '#')
            )
            val expected = arrayOf(
                3, 4, 5, 5, 3,
                4, 6, 6, 8, 5,
                5, 5, 8, 8, 5,
                5, 7, 8, 8, 5,
                4, 7, 7, 8, 5,
                3, 5, 5, 5, 3
            )
            for (row in grid.indices) {
                for (col in 0 until grid[0].size) {
                    val occupied = occupiedSeatsInLineOfSight(grid, row, col)
                    occupiedValues.add(occupied)
                    print("$occupied,")
                }
                println()
            }
            for (array in grid) {
                for (c in array) print("$c ")
                println()
            }
            val actual = occupiedValues.toTypedArray()
            Assertions.assertArrayEquals(expected, actual)
        }

    @Test
    fun isOccupiedAfterUpdate5() {
            val occupiedValues: MutableList<Int> = ArrayList()
            val grid = arrayOf(
                charArrayOf('#', '.', '#', '#', '.', '#', '#', '.', '#', '#'),
                charArrayOf('#', '#', '#', '#', '#', '#', '#', '.', '#', '#'),
                charArrayOf('#', '.', '#', '.', '#', '.', '.', '#', '.', '.'),
                charArrayOf('#', '#', '#', '#', '.', '#', '#', '.', '#', '#'),
                charArrayOf('#', '.', '#', '#', '.', '#', '#', '.', '#', '#'),
                charArrayOf('#', '.', '#', '#', '#', '#', '#', '.', '#', '#'),
                charArrayOf('.', '.', '#', '.', '#', '.', '.', '.', '.', '.'),
                charArrayOf('#', '#', '#', '#', '#', '#', '#', '#', '#', '#'),
                charArrayOf('#', '.', '#', '#', '#', '#', '#', '#', '.', '#'),
                charArrayOf('#', '.', '#', '#', '#', '#', '#', '.', '#', '#')
            )
            val expected = arrayOf(
                3, 5, 5, 5, 5, 5, 5, 5, 5, 3,
                4, 7, 7, 7, 7, 7, 7, 7, 6, 5,
                5, 8, 8, 8, 8, 8, 8, 6, 7, 5,
                5, 8, 8, 8, 8, 8, 8, 8, 7, 5,
                5, 8, 7, 8, 8, 8, 8, 8, 8, 5,
                5, 7, 8, 8, 8, 8, 8, 8, 7, 5,
                5, 7, 7, 8, 7, 7, 7, 7, 7, 5,
                5, 6, 8, 8, 8, 8, 8, 8, 7, 4,
                4, 7, 7, 8, 8, 8, 7, 7, 7, 5,
                3, 5, 5, 5, 5, 5, 5, 5, 5, 3
            )
            for (row in grid.indices) {
                for (col in 0 until grid[0].size) {
                    val occupied = occupiedSeatsInLineOfSight(grid, row, col)
                    occupiedValues.add(occupied)
                    print("$occupied,")
                }
                println()
            }
            for (array in grid) {
                for (c in array) print("$c ")
                println()
            }
            val actual = occupiedValues.toTypedArray()
            Assertions.assertArrayEquals(expected, actual)
        }

    private fun occupiedSeatsInLineOfSight(curGrid: Array<CharArray>, row: Int, col: Int): Int {
        var occupiedInLineOfSight = 0
        for (dir in DIRECTIONS) {
            if (occupiedInLineOfSight(curGrid, row, col, dir)) occupiedInLineOfSight++
        }
        return occupiedInLineOfSight
    }

    private fun occupiedInLineOfSight(curGrid: Array<CharArray>, row: Int, col: Int, dir: IntArray): Boolean {
        var dRow = row
        var dCol = col
        while (dRow >= 0 && dRow < curGrid.size && dCol >= 0 && dCol < curGrid[0].size) {
            if (row != dRow || col != dCol) {
                val checked = curGrid[dRow][dCol]
                if (checked != Day11Challenge.FLOOR) {
                    if (checked == Day11Challenge.OCCUPIED_SEAT) return true
                    break
                }
            }
            dCol += dir[0]
            dRow += dir[1]
        }
        return false
    }

    companion object {
        private val DIRECTIONS = arrayOf(
            intArrayOf(1, 0),
            intArrayOf(1, 1),
            intArrayOf(0, 1),
            intArrayOf(-1, 1),
            intArrayOf(-1, 0),
            intArrayOf(-1, -1),
            intArrayOf(0, -1),
            intArrayOf(1, -1)
        )
    }
}

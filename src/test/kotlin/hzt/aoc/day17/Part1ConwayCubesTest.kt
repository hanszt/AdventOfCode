package hzt.aoc.day17

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Part1ConwayCubesTest {
    private val conwayCubes = Part1ConwayCubes()
    @Test
    fun countActiveNeighbors() {
        val input = arrayOf(intArrayOf(0, 1, 0), intArrayOf(0, 0, 1), intArrayOf(1, 1, 1))
        val grid = convertIntArrayToBooleanList(input)
        println(conwayCubes.grid3DAsString(grid))
        val neighbours = conwayCubes.countActiveNeighbors(Point(1, 2, 0), grid)
        Assertions.assertEquals(3, neighbours)
    }

    private fun convertIntArrayToBooleanList(input: Array<IntArray>):
            MutableList<MutableList<MutableList<Boolean>>> {
        val grid3D: MutableList<MutableList<MutableList<Boolean>>> = ArrayList()
        val grid2D: MutableList<MutableList<Boolean>> = ArrayList()
        for (intRow in input) {
            val row: MutableList<Boolean> = ArrayList()
            for (value in intRow) {
                row.add(value == 1)
            }
            grid2D.add(row)
        }
        grid3D.add(grid2D)
        return grid3D
    }
}

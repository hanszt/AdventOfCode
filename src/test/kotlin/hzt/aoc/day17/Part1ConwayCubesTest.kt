package hzt.aoc.day17

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Part1ConwayCubesTest {
    private val conwayCubes = Part1ConwayCubes()

    @Test
    fun countActiveNeighbors() {
        val input = arrayOf(
            intArrayOf(0, 1, 0),
            intArrayOf(0, 0, 1),
            intArrayOf(1, 1, 1)
        )

        val grid = listOf(input.map { array -> array.map { it == 1 } }.toList())
        println(conwayCubes.grid3DAsString(grid))
        val neighbours = conwayCubes.countActiveNeighbors(Point(1, 2, 0), grid)
        assertEquals(3, neighbours)
    }

}

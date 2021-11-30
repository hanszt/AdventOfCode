package hzt.aoc.day20

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class TileTest {
    @Test
    fun testOrientationsWithP() {
        val list: MutableList<String> = ArrayList()
        list.add("#### ")
        list.add("#   #")
        list.add("#### ")
        list.add("#    ")
        list.add("#    ")
        val tile = Tile(list)
        println(tile.orientationsAsString())
        Assertions.assertTrue(true)
    }
}

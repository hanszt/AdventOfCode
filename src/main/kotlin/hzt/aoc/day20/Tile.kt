package hzt.aoc.day20

import java.awt.Point

// Credits to Johan de Jong
class Tile(content: List<String>) {

    var position: Point = Point()
    private val content: List<String>
    val orientations: List<List<String>>

    private fun tileSides(): List<String> {
        val sidesWithoutFlip: MutableList<String> = ArrayList()
        sidesWithoutFlip.add(content[0])
        sidesWithoutFlip.add(content[content.size - 1])
        val left = StringBuilder()
        val right = StringBuilder()
        for (line in content) {
            left.append(line[0])
            right.append(line[line.length - 1])
        }
        sidesWithoutFlip.add(left.toString())
        sidesWithoutFlip.add(right.toString())
        val sides: MutableList<String> = ArrayList()
        for (side in sidesWithoutFlip) {
            sides.add(side)
            val sb = StringBuilder(side)
            sb.reverse()
            sides.add(sb.toString())
        }
        return sides
    }

    fun isBorder(tiles: Map<Int, Tile>): Boolean {
        val otherTiles = otherTileBorders(tiles)
        val sideTiles: Set<String> = HashSet(tileSides())
        return countCommonElements(sideTiles, otherTiles) == CORNERS
    }

    private fun countCommonElements(sideTiles: Set<String>, otherTiles: Set<String>) =
        sideTiles.count(otherTiles::contains)

    private fun otherTileBorders(tiles: Map<Int, Tile>): Set<String> {
        val otherSet: MutableSet<String> = HashSet()
        for (otherTile in tiles.values) {
            if (equals(otherTile).not()) {
                otherSet.addAll(otherTile.tileSides())
            }
        }
        return otherSet
    }

    private fun calculateOrientations(): List<List<String>> {
        val result: MutableList<List<String>> = ArrayList()
        collectRotations(result, content)
        collectRotations(result, flip())
        return result
    }

    private fun collectRotations(result: MutableList<List<String>>, input: List<String>) {
        result.add(input)
        var temp = rotate(input)
        result.add(temp)
        temp = rotate(temp)
        result.add(temp)
        temp = rotate(temp)
        result.add(temp)
    }

    private fun flip(): List<String> {
        val result: MutableList<String> = ArrayList()
        for (line in content) {
            val sb = StringBuilder(line)
            sb.reverse()
            result.add(sb.toString())
        }
        return result
    }

    private fun rotate(original: List<String>): List<String> {
        val result: MutableList<String> = ArrayList()
        for (x in original.indices) {
            val sb = StringBuilder()
            for (y in original.indices.reversed()) {
                val c: Char = original[y][x]
                sb.append(c)
            }
            result.add(sb.toString())
        }
        return result
    }

    private fun asString(content: List<String>): String {
        val sb = StringBuilder()
        sb.append(String.format("%n"))
        content.forEach { sb.append(it).append(String.format("%n")) }
        return sb.toString()
    }

    fun orientationsAsString(): String {
        val sb = StringBuilder()
        sb.append(String.format("%n"))
        orientations.forEach { sb.append(asString(it)) }
        return sb.toString()
    }

    val top: String
        get() = content[0]
    val bottom: String
        get() = content[content.size - 1]
    val left: String
        get() {
            val sb = StringBuilder()
            for (line in content) {
                sb.append(line[0])
            }
            return sb.toString()
        }
    val right: String
        get() {
            val sb = StringBuilder()
            for (line in content) {
                sb.append(line[line.length - 1])
            }
            return sb.toString()
        }
    val inner: List<String>
        get() {
            val result: MutableList<String> = ArrayList()
            for (i in 1 until content.size - 1) {
                val line = content[i]
                result.add(line.substring(1, line.length - 1))
            }
            return result
        }

    override fun toString(): String {
        return "Tile{" +
                "content=" + asString(content) +
                '}'
    }

    companion object {
        private const val CORNERS = 4
    }

    init {
        this.content = content
        orientations = calculateOrientations()
    }
}

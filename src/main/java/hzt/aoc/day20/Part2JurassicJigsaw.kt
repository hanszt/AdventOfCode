package hzt.aoc.day20

import java.awt.Point
import kotlin.math.sqrt

// Credits to Johan de Jong
class Part2JurassicJigsaw : Day20Challenge(
    "part 2",
    "Determine how rough the waters are in the sea monsters' habitat by counting the number of # " +
            "that are not part of a sea monster. How many # are not part of a sea monster"
) {
    private var sideLength = 0
    override fun calculateAnswer(tileIdsToGrids: Map<Int, Tile>): Long {
        sideLength = sqrt(tileIdsToGrids.size.toDouble()).toInt()
        val pictureSideLength = sqrt(tileIdsToGrids.size.toDouble()).toInt()
        var result: Long = 0
        val placedIds: MutableSet<Int> = HashSet()
        val placedTiles = Array(pictureSideLength) { arrayOfNulls<Tile>(pictureSideLength) }
        val complete = placeNextTile(placedIds, placedTiles, Point(0, 0), tileIdsToGrids)
        if (complete) {
            val fullPicture = buildFullPicture(placedTiles, pictureSideLength)
            result = countHowManyHashesNotPartOfSeeMonster(fullPicture)
        }
        return result
    }

    private fun countHowManyHashesNotPartOfSeeMonster(fullPicture: List<String>): Long {
        val seeMonster = createSeeMonster()
        val fullPictureTile = Tile(fullPicture)
        for (orientation in fullPictureTile.orientations) {
            val markedList: MutableList<String> = ArrayList(orientation)
            var marked = false
            for (y in 0..orientation.size - seeMonster.size) {
                for (x in 0..orientation[0].length - seeMonster[0].length) {
                    if (isPatternAt(orientation, seeMonster, x, y)) {
                        markPatternAt(markedList, seeMonster, x, y)
                        marked = true
                    }
                }
            }
            if (marked) return countHashes(markedList).toLong()
        }
        return 0
    }

    private fun buildFullPicture(placedTile: Array<Array<Tile?>>, pictureSideLength: Int): List<String> {
        val fullPicture: MutableList<String> = ArrayList()
        for (y in 0 until pictureSideLength) {
            val inners: MutableList<List<String>> = ArrayList()
            for (x in 0 until pictureSideLength) inners.add(placedTile[y][x]?.inner ?: emptyList())
            for (j in inners[0].indices) {
                val sb = StringBuilder()
                for (inner in inners) sb.append(inner[j])
                fullPicture.add(sb.toString())
            }
        }
        return fullPicture
    }

    private fun createSeeMonster(): List<String> {
        val seeMonster: MutableList<String> = ArrayList()
        seeMonster.add("                  # ")
        seeMonster.add("#    ##    ##    ###")
        seeMonster.add(" #  #  #  #  #  #   ")
        return seeMonster
    }

    private fun isPatternAt(fullPicture: List<String>, pattern: List<String>, x: Int, y: Int): Boolean {
        for (dy in pattern.indices) {
            for (dx in 0 until pattern[0].length) {
                val matchesHashInPattern = pattern[dy][dx] == '#'
                val noMatchPictureCurSpot = fullPicture[y + dy][x + dx] != '#'
                if (matchesHashInPattern && noMatchPictureCurSpot) {
                    return false
                }
            }
        }
        return true
    }

    private fun markPatternAt(fullPicture: MutableList<String>, pattern: List<String>, x: Int, y: Int) {
        for (dy in pattern.indices) {
            for (dx in 0 until pattern[0].length) {
                if (pattern[dy][dx] == '#') {
                    val originalLine = fullPicture[y + dy]
                    val position = x + dx
                    val newLine = originalLine.substring(0, position) + "O" + originalLine.substring(position + 1)
                    fullPicture[y + dy] = newLine
                }
            }
        }
    }

    private fun countHashes(fullPicture: List<String>): Int {
        var count = 0
        for (line in fullPicture) {
            for (element in line) {
                if (element == '#') {
                    count++
                }
            }
        }
        return count
    }

    private fun placeNextTile(placedIds: MutableSet<Int>, placed: Array<Array<Tile?>>,
        curPosition: Point, tiles: Map<Int, Tile>
    ): Boolean {
        for ((key, curTile) in tiles) {
            if (!placedIds.contains(key)) {
                curTile.position = curPosition
                placedIds.add(key)
                if (orientationFits(placed, curTile, placedIds, tiles)) return true
                placedIds.remove(key)
            }
        }
        return false
    }

    private fun orientationFits(
        placed: Array<Array<Tile?>>,
        curTile: Tile,
        placedIds: MutableSet<Int>,
        tiles: Map<Int, Tile>
    ): Boolean {
        for (orientation in curTile.orientations) {
            val cur = curTile.position
            placed[cur.y][cur.x] = Tile(orientation)
            if (canPlaceTile(placed, cur)) {
                val next = nextPosition(cur)
                val allTilesPlaced = next.y == sideLength
                if (allTilesPlaced || placeNextTile(placedIds, placed, next, tiles)) return true
            }
        }
        return false
    }

    private fun nextPosition(point: Point): Point {
        var nextX = point.x + 1
        var nextY = point.y
        if (nextX == sideLength) {
            nextX = 0
            nextY++
        }
        return Point(nextX, nextY)
    }

    private fun canPlaceTile(placed: Array<Array<Tile?>>, p: Point): Boolean {
        if (p.x > 0 && placed[p.y][p.x - 1]?.right != placed[p.y][p.x]?.left) {
            return false
        }
        return if (p.y > 0) {
            placed[p.y - 1][p.x]?.bottom == placed[p.y][p.x]?.top
        } else true
    }

    override fun getMessage(value: Long): String {
        return String.format("%d", value)
    }
}

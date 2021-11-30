package hzt.aoc.day24

import hzt.aoc.Challenge
import java.awt.Point
import java.util.function.Consumer
import java.util.stream.Collectors

abstract class Day24Challenge internal constructor(part: String, description: String) :
    Challenge(part, description, "20201224-input-day24ref.txt") {
    
    override fun solve(inputList: List<String>): String {
        val instructions: MutableList<List<String>> = ArrayList()
        for (line in inputList) {
            val instruction: MutableList<String> = ArrayList()
            val parsedLine = line.replace(Tile.SOUTH_EAST, " " + Tile.SOUTH_EAST + ",")
                .replace(Tile.SOUTH_WEST, " " + Tile.SOUTH_WEST + ",")
                .replace(Tile.NORTH_WEST, " " + Tile.NORTH_WEST + ",")
                .replace(Tile.NORTH_EAST, " " + Tile.NORTH_EAST + ",")
            val array = parsedLine.split(COMMA_OR_COMMA_WITH_SPACE.toRegex()).toTypedArray()
            for (string in array) {
                if (string.length != 2 || !INSTRUCTION_SET.contains(string)) {
                    instruction.addAll(string.chars()
                        .mapToObj(Int::toChar)
                        .map(Char::toString)
                        .map(String::trim)
                        .collect(Collectors.toList()))
                } else if (string.isNotBlank()) {
                    instruction.add(string.trim())
                }
            }
            instructions.add(instruction)
        }
        LOGGER.trace(listOfStringListsAsString(instructions))
        return getMessage(calculateResult(instructions))
    }

    fun buildFloorByInstructions(instructionsList: List<List<String>>): Map<Point, Tile> {
        val tileMap: MutableMap<Point, Tile> = HashMap()
        val centerTile = Tile(Point(0, 0))
        tileMap[centerTile.position] = centerTile
        for (instructions in instructionsList) {
            var curTile: Tile? = centerTile
            for (instruction in instructions) {
                curTile = curTile?.getNeighborByInstruction(instruction, tileMap)
                if (curTile != null) tileMap[curTile.position] = curTile
            }
            curTile?.flip()
        }
        tileMap.values.forEach(Consumer { message: Tile -> LOGGER.trace(message) })
        return tileMap
    }

    fun countTilesWithBlackSideUp(tiles: Collection<Tile>): Long {
        return tiles.stream().filter { obj: Tile -> obj.isBlackUp }.count()
    }

    protected abstract fun calculateResult(instructions: List<List<String>>): Long
    abstract fun getMessage(value: Long): String

    companion object {
        val INSTRUCTION_SET = setOf(
            Tile.EAST,
            Tile.SOUTH_EAST,
            Tile.SOUTH_WEST,
            Tile.WEST,
            Tile.NORTH_WEST,
            Tile.NORTH_EAST
        )
        private const val COMMA_OR_COMMA_WITH_SPACE = "\\s[, ]\\s"
    }
}

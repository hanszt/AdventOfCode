package hzt.aoc.day20

import hzt.aoc.Challenge

abstract class Day20Challenge internal constructor(challengeTitle: String, description: String) :
    Challenge(challengeTitle, description, "20201220-input-day20.txt") {
    override fun solve(inputList: List<String>): String {
        val tileIdsToGrids = parseInput(inputList)
        return getMessage(calculateAnswer(tileIdsToGrids))
    }

    private fun parseInput(inputList: List<String>): Map<Int, Tile> {
        val tileIdsToGrids: MutableMap<Int, Tile> = HashMap()
        var tileContent: MutableList<String> = ArrayList()
        var tileId = 0
        for (line in inputList) {
            if (line.contains("Tile")) {
                tileId = line.replace("Tile ", "").replace(":", "").trim().toInt()
                tileContent = ArrayList()
            } else if (line.isNotEmpty()) {
                tileContent.add(line)
            }
            if (line.isBlank()) {
                tileIdsToGrids[tileId] = Tile(tileContent)
            }
        }
        tileIdsToGrids.forEach { (id, tile) -> LOGGER.trace("$id->$tile") }
        LOGGER.trace(tileIdsToGrids.size)
        return tileIdsToGrids
    }

    protected abstract fun calculateAnswer(tileIdsToGrids: Map<Int, Tile>): Long
    abstract fun getMessage(value: Long): String
}

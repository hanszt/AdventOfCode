package hzt.aoc.day24

import java.lang.StringBuilder
import java.util.HashMap
import java.util.Objects
import java.awt.Point
import java.util.ArrayList

class Tile(val position: Point) {

    companion object {
        const val EAST = "e"
        const val SOUTH_EAST = "se"
        const val SOUTH_WEST = "sw"
        const val WEST = "w"
        const val NORTH_WEST = "nw"
        const val NORTH_EAST = "ne"
        private val OPPOSITE_DIR: MutableMap<String, String> = HashMap()
        private val INSTRUCTION_TO_DIR: MutableMap<String, Point> = HashMap()

        init {
            OPPOSITE_DIR[EAST] = WEST
            OPPOSITE_DIR[WEST] = EAST
            OPPOSITE_DIR[SOUTH_EAST] = NORTH_WEST
            OPPOSITE_DIR[NORTH_WEST] = SOUTH_EAST
            OPPOSITE_DIR[SOUTH_WEST] = NORTH_EAST
            OPPOSITE_DIR[NORTH_EAST] =
                SOUTH_WEST
            INSTRUCTION_TO_DIR[EAST] = Point(1, 0)
            INSTRUCTION_TO_DIR[WEST] = Point(-1, 0)
            INSTRUCTION_TO_DIR[SOUTH_EAST] = Point(1, -1)
            INSTRUCTION_TO_DIR[NORTH_WEST] = Point(-1, 1)
            INSTRUCTION_TO_DIR[SOUTH_WEST] = Point(0, -1)
            INSTRUCTION_TO_DIR[NORTH_EAST] = Point(0, 1)
        }
    }

    var isBlackUp = false
        private set
    private var nrOfBlackNeighbors = 0
    private val instructionsToNeighborsMap: MutableMap<Point?, Tile> = HashMap()
    fun flip() {
        isBlackUp = !isBlackUp
    }

    fun getNeighborByInstruction(instruction: String, allTiles: Map<Point, Tile>): Tile? {
        val delta: Point = INSTRUCTION_TO_DIR[instruction] ?: Point()
        val newPosition = Point(position.x + delta.x, position.y + delta.y)
        val neighbor: Tile? = if (instructionsToNeighborsMap[delta] != null) {
            instructionsToNeighborsMap[delta]
        } else if (allTiles.containsKey(newPosition)) {
            allTiles[newPosition]
        } else {
            Tile(newPosition)
        }
        if (neighbor != null) {
            neighbor.instructionsToNeighborsMap[INSTRUCTION_TO_DIR[OPPOSITE_DIR[instruction]]] = this
            instructionsToNeighborsMap[INSTRUCTION_TO_DIR[instruction]] = neighbor
        }
        return neighbor
    }

    fun countBlackNeighbors() {
        nrOfBlackNeighbors = 0
        for (neighbor in instructionsToNeighborsMap.values) {
            if (neighbor.isBlackUp) nrOfBlackNeighbors++
        }
    }

    fun countBlackNeighbors(allTiles: Map<Point, Tile>) {
        nrOfBlackNeighbors = 0
        for (delta in INSTRUCTION_TO_DIR.values) {
            val neighborPosition = Point(position.x + delta.x, position.y + delta.y)
            if (allTiles.containsKey(neighborPosition)) {
                val neighbor = allTiles[neighborPosition]
                if (neighbor?.isBlackUp == true) nrOfBlackNeighbors++
            }
        }
    }

    //    Any black tile with zero or more than 2 black tiles immediately adjacent to it is flipped to white.
    //    Any white tile with exactly 2 black tiles immediately adjacent to it is flipped to black.
    fun executeRule() {
        if (isBlackUp && (nrOfBlackNeighbors == 0 || nrOfBlackNeighbors > 2)) flip()
        if (!isBlackUp && nrOfBlackNeighbors == 2) flip()
    }

    fun neighbors(): List<Tile> {
        val neighbors: MutableList<Tile> = ArrayList()
        for (delta in INSTRUCTION_TO_DIR.values) {
            neighbors.add(Tile(Point(position.x + delta.x, position.y + delta.y)))
        }
        return neighbors
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val tile = o as Tile
        return position == tile.position
    }

    override fun hashCode(): Int {
        return Objects.hash(position)
    }

    private fun neighborsAsString(): String {
        val sb = StringBuilder()
        instructionsToNeighborsMap.entries.forEach { sb.append(neighborAsString(it)) }
        return sb.toString()
    }

    private fun neighborAsString(e: Map.Entry<Point?, Tile>): String {
        val p = e.value.position
        val delta = e.key
        return String.format(
            "delta(x=%2d, y=%2d)->(position='(x=%3d, y=%3d)', blackUp=%5b) ",
            delta?.x, delta?.y, p.x, p.y, e.value.isBlackUp
        )
    }

    override fun toString(): String {
        return String.format(
            "Tile{position='(x=%3d, y=%3d)', blackUp=%-5b, nrOfBlackNeighbors=%d, Neighbors={%s}}",
            position.x, position.y, isBlackUp, nrOfBlackNeighbors, neighborsAsString()
        )
    }
}

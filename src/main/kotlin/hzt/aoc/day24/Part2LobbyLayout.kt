package hzt.aoc.day24

import java.util.stream.Collectors

// Credits to Johan de Jong
class Part2LobbyLayout : Day24Challenge(
    "part 2",
    "How many tiles will be black after 100 days"
) {
    override fun calculateResult(instructions: List<List<String>>): Long {
        val tileMap = buildFloorByInstructions(instructions)
        val blackTiles = tileMap.values.stream().filter { obj: Tile -> obj.isBlackUp }.collect(Collectors.toSet())
        for (day in 0 until DAYS_OF_EXHIBIT) {
            simulate(blackTiles)
        }
        return blackTiles.size.toLong()
    }

    private fun simulate(blackTiles: MutableSet<Tile>) {
        val active = determineActiveSet(blackTiles)
        val originalBlack: Set<Tile> = HashSet(blackTiles)
        for (position in active) {
            val blackNeighbours = countBlackNeighbours(originalBlack, position)
            if (originalBlack.contains(position) && (blackNeighbours == 0L || blackNeighbours > 2)) {
                blackTiles.remove(position)
            } else if (blackNeighbours == 2L) {
                blackTiles.add(position)
            }
        }
    }

    private fun countBlackNeighbours(originalBlack: Set<Tile>, startTile: Tile): Long {
        return startTile.neighbors().stream()
            .filter { originalBlack.contains(it) }
            .count()
    }

    private fun determineActiveSet(blackTiles: Set<Tile>): Set<Tile> {
        val active: MutableSet<Tile> = HashSet(blackTiles)
        for (position in blackTiles) {
            active.addAll(position.neighbors())
        }
        return active
    }

    override fun getMessage(value: Long): String {
        return String.format("%d", value)
    }

    companion object {
        private const val DAYS_OF_EXHIBIT = 100
    }
}

package hzt.aoc.day20

// Credits to Johan de Jong
class Part1JurassicJigsaw : Day20Challenge(
    "part 1",
    "Assemble the tiles into an image. What do you get if you multiply together the IDs of the four corner tiles"
) {
    override fun calculateAnswer(tileIdsToGrids: Map<Int, Tile>): Long {
        var result = 1L
        for ((key, tile) in tileIdsToGrids) {
            if (tile.isBorder(tileIdsToGrids)) {
                result *= key
            }
        }
        return result
    }

    override fun getMessage(value: Long): String {
        return String.format("%d", value)
    }
}

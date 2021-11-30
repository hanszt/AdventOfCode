package hzt.aoc.day24

class Part1LobbyLayout : Day24Challenge(
    "part 1",
    "Go through the renovation crew's list and determine which tiles they need to flip. " +
            "After all of the instructions have been followed, how many tiles are left with the black side up"
) {
    override fun calculateResult(instructions: List<List<String>>): Long {
        val tileMap = buildFloorByInstructions(instructions)
        return countTilesWithBlackSideUp(tileMap.values)
    }

    override fun getMessage(value: Long): String {
        return String.format("%d", value)
    }
}

package hzt.aoc.day03

import java.awt.Point

class TreesEncounteredPart2 : Day03Challenge(
    "part 2",
    "Find the product of the number of trees crossed by all the given paths"
) {
    override fun calculateResult(grid: List<List<Boolean>>): Long {
        var product: Long = 1
        for (path in Path.values()) {
            val numberOfTrees = calculateNumberOfTreesEncountered(grid, Point(0, 0), path.slope)
            LOGGER.info(
                String.format(
                    "The number of trees crossed using %s is %d",
                    path.name,
                    numberOfTrees
                )
            )
            product *= numberOfTrees.toLong()
        }
        LOGGER.info("")
        return product
    }

    override fun getMessage(result: String): String {
        return String.format("The product of all the number of trees crossed is: %s%n", result)
    }
}

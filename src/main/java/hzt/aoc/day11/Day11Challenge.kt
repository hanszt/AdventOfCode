package hzt.aoc.day11

import hzt.aoc.Challenge

abstract class Day11Challenge internal constructor(
    challengeTitle: String,
    description: String,
    inputFileName: String
) : Challenge(challengeTitle, description, inputFileName) {

    override fun getMessage(result: String): String {
        return String.format("The number of seats occupied after equilibrium: %s%n", result)
    }

    companion object {
        const val EMPTY_SEAT = 'L'
        const val OCCUPIED_SEAT = '#'
        const val FLOOR = '.'
    }
}

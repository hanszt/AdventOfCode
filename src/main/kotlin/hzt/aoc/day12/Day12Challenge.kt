package hzt.aoc.day12

import hzt.aoc.Challenge
import java.awt.Point

abstract class Day12Challenge internal constructor(challengeTitle: String, description: String) :
    Challenge(challengeTitle, description, "20201212-input-day12.txt") {
    fun translatePointInWindDirection(position: Point, direction: Char, amount: Int) {
        if (direction == EAST) position.translate(amount, 0)
        if (direction == WEST) position.translate(-amount, 0)
        if (direction == NORTH) position.translate(0, amount)
        if (direction == SOUTH) position.translate(0, -amount)
    }

    abstract fun getMessage(value: Int): String

    companion object {
        const val EAST = 'E'
        const val WEST = 'W'
        const val NORTH = 'N'
        const val SOUTH = 'S'
        const val TURN_LEFT = 'L'
        const val TURN_RIGHT = 'R'
        const val MOVE_FORWARD = 'F'
        const val NINETY_DEGREES = 90
    }
}

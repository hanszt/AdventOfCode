package hzt.aoc.day12

import java.awt.Point
import kotlin.math.abs

class Part1RainRisk : Day12Challenge(
    "part 1",
    "What is the Manhattan distance between that location and the ship's starting position See ChallengeDAy12.md"
) {
    override fun solve(inputList: List<String>): String {
        val position = Point(0, 0)
        var orientation = Point(1, 0)
        for (line in inputList) {
            val instruction = line[0]
            val amount = line.substring(1).toInt()
            orientation = rotateShip(orientation, instruction, amount)
            translatePointInWindDirection(position, instruction, amount)
            if (instruction == MOVE_FORWARD) position.translate(
                orientation.x * amount,
                orientation.y * amount
            )
        }
        return getMessage(abs(position.x) + abs(position.y))
    }

    private fun rotateShip(orientation: Point, instruction: Char, angle: Int): Point {
        var newOrientation = orientation
        if ((instruction == TURN_RIGHT || instruction == TURN_LEFT) && angle % NINETY_DEGREES == 0) {
            val dir = if (instruction == TURN_RIGHT) 1 else -1
            var counter = 0
            while (angle / NINETY_DEGREES != counter) {
                when (newOrientation) {
                    Point(1, 0) -> newOrientation = Point(0, -dir)
                    Point(0, 1) -> newOrientation = Point(dir, 0)
                    Point(-1, 0) -> newOrientation = Point(0, dir)
                    Point(0, -1) -> newOrientation = Point(-dir, 0)
                }
                counter++
            }
        }
        return newOrientation
    }

    override fun getMessage(value: Int): String = String.format("%d", value)
}

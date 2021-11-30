package hzt.aoc.day12

import java.awt.Point
import kotlin.math.abs

class Part2RainRisk : Day12Challenge(
    "part 2",
    "Almost all of the actions indicate how to move a waypoint which is relative to the ship's position: (See ChallengeDay12.md)" +
            "What is the Manhattan distance between that location and the ship's starting position"
) {
    override fun solve(inputList: List<String>): String {
        val wayPoint = Point(10, 1)
        val ship = Point(0, 0)
        for (line in inputList) {
            val instruction = line[0]
            val amount = line.substring(1).toInt()
            rotateWayPointRoundShip(wayPoint, instruction, amount)
            translatePointInWindDirection(wayPoint, instruction, amount)
            if (instruction == MOVE_FORWARD) ship.translate(
                wayPoint.x * amount,
                wayPoint.y * amount
            )
        }
        return getMessage(abs(ship.x) + abs(ship.y))
    }

    private fun rotateWayPointRoundShip(wayPoint: Point, instruction: Char, angle: Int) {
        if ((instruction == TURN_RIGHT || instruction == TURN_LEFT) && angle % NINETY_DEGREES == 0) {
            val dir = if (instruction == TURN_RIGHT) Point(1, -1) else Point(-1, 1)
            var counter = 0
            while (angle / NINETY_DEGREES != counter) {
                wayPoint.move(dir.x * wayPoint.y, dir.y * wayPoint.x)
                counter++
            }
        }
    }

    override fun getMessage(value: Int): String = String.format("%d", value)
}

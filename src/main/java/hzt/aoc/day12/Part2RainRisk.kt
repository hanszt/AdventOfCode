package hzt.aoc.day12;

import java.awt.*;
import java.util.List;

public class Part2RainRisk extends Day12Challenge {

    public Part2RainRisk() {
        super("part 2",
                "Almost all of the actions indicate how to move a waypoint which is relative to the ship's position: (See ChallengeDay12.md)" +
                        "What is the Manhattan distance between that location and the ship's starting position?");
    }

    @Override
    protected String solve(List<String> inputList) {
        Point wayPoint = new Point(10, 1);
        Point ship = new Point(0, 0);
        for (String line : inputList) {
            char instruction = line.charAt(0);
            int amount = Integer.parseInt(line.substring(1));
            rotateWayPointRoundShip(wayPoint, instruction, amount);
            translatePointInWindDirection(wayPoint, instruction, amount);
            if (instruction == MOVE_FORWARD) ship.translate(wayPoint.x * amount, wayPoint.y * amount);
        }
        return getMessage(Math.abs(ship.x) + Math.abs(ship.y));
    }

    private void rotateWayPointRoundShip(Point wayPoint, char instruction, int angle) {
        if ((instruction == TURN_RIGHT || instruction == TURN_LEFT) && angle % NINETY_DEGREES == 0) {
            Point dir = instruction == TURN_RIGHT ? new Point(1, -1) : new Point(-1, 1);
            int counter = 0;
            while (angle / NINETY_DEGREES != counter) {
                wayPoint.move(dir.x * wayPoint.y, dir.y * wayPoint.x);
                counter++;
            }
        }
    }

    @Override
    String getMessage(int value) {
        return String.format("%d", value);
    }
}

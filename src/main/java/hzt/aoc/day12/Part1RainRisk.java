package hzt.aoc.day12;

import java.awt.*;
import java.util.List;

public class Part1RainRisk extends Day12Challenge {

    public Part1RainRisk() {
        super("part 1",
                "What is the Manhattan distance between that location and the ship's starting position? See ChallengeDAy12.md");
    }

    @Override
    protected Object solve(List<String> inputList) {
        Point position = new Point(0, 0);
        Point orientation = new Point(1, 0);
        for (String line : inputList) {
            char instruction = line.charAt(0);
            int amount = Integer.parseInt(line.substring(1));
            orientation = rotateShip(orientation, instruction, amount);
            translatePointInWindDirection(position, instruction, amount);
            if (instruction == MOVE_FORWARD) position.translate(orientation.x * amount, orientation.y * amount);
        }
        return getMessage(Math.abs(position.x) + Math.abs(position.y));
    }

    private Point rotateShip(Point orientation, char instruction, int angle) {
        if ((instruction == TURN_RIGHT || instruction == TURN_LEFT) && angle % NINETY_DEGREES == 0) {
            int dir = instruction == TURN_RIGHT ? 1 : -1;
            int counter = 0;
            while (angle / NINETY_DEGREES != counter) {
                if (orientation.equals(new Point(1, 0))) orientation = new Point(0, -dir);
                else if (orientation.equals(new Point(0, 1))) orientation = new Point(dir, 0);
                else if (orientation.equals(new Point(-1, 0))) orientation = new Point(0, dir);
                else if (orientation.equals(new Point(0, -1))) orientation = new Point(-dir, 0);
                counter++;
            }
        }
        return orientation;
    }

    @Override
    String getMessage(int global) {
        return String.format("%d", global);
    }
}

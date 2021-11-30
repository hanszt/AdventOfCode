package hzt.aoc.day12;

import hzt.aoc.Challenge;

import java.awt.*;

public abstract class Day12Challenge extends Challenge {

    Day12Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201212-input-day12.txt");
    }

    static final char EAST = 'E';
    static final char WEST = 'W';
    static final char NORTH = 'N';
    static final char SOUTH = 'S';
    static final char TURN_LEFT = 'L';
    static final char TURN_RIGHT = 'R';
    static final char MOVE_FORWARD = 'F';

    static final int NINETY_DEGREES = 90;

    void translatePointInWindDirection(Point position, char direction, int amount) {
        if (direction == EAST) position.translate(amount, 0);
        if (direction == WEST) position.translate(-amount, 0);
        if (direction == NORTH) position.translate(0, amount);
        if (direction == SOUTH) position.translate(0, -amount);
    }


    abstract String getMessage(int value);

}

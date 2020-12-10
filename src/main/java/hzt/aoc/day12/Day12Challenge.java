package hzt.aoc.day12;

import hzt.aoc.Challenge;

import java.util.List;

public abstract class Day12Challenge extends Challenge {

    Day12Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201209-input-day9.txt");
    }

    @Override
    protected String solve(List<String> inputList) {


        return getMessage(0);
    }




    abstract String getMessage(long value);
}

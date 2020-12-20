package hzt.aoc.day20;

import hzt.aoc.Challenge;

import java.util.List;

public abstract class Day20Challenge extends Challenge {

    Day20Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201220-input-day20.txt");
    }

    @Override
    protected String solve(List<String> inputList) {


        return getMessage(0);
    }




    abstract String getMessage(long value);
}

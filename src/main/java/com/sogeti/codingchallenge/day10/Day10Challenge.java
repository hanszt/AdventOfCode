package com.sogeti.codingchallenge.day10;

import com.sogeti.codingchallenge.Challenge;

import java.util.ArrayList;
import java.util.List;

public abstract class Day10Challenge extends Challenge {

    Day10Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201209-input-day9.txt");
    }

    @Override
    protected String solve(List<String> inputList) {
        List<?> list = new ArrayList<>();
        return getMessage(solveByList(list));
    }

    protected abstract long solveByList(List<?> list);


    abstract String getMessage(long value);
}

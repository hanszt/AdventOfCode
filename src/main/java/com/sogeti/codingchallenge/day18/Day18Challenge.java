package com.sogeti.codingchallenge.day18;

import com.sogeti.codingchallenge.Challenge;

import java.util.List;

public abstract class Day18Challenge extends Challenge {

    Day18Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201209-input-day9.txt");
    }

    @Override
    protected String solve(List<String> inputList) {


        return getMessage(0);
    }




    abstract String getMessage(long value);
}

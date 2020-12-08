package com.sogeti.codingchallenge.day25;

import com.sogeti.codingchallenge.Challenge;

import java.util.List;

public abstract class Day25Challenge extends Challenge {

    Day25Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201209-input-day9.txt");
    }

    @Override
    protected String solve(List<String> inputList) {


        return getMessage(0);
    }




    abstract String getMessage(long value);
}

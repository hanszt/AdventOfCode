package com.sogeti.codingchallenge.day23;

import com.sogeti.codingchallenge.Challenge;

import java.util.List;

public abstract class Day23Challenge extends Challenge {

    Day23Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201209-input-day9.txt");
    }

    @Override
    protected String solve(List<String> inputList) {


        return getMessage(0);
    }




    abstract String getMessage(long value);
}

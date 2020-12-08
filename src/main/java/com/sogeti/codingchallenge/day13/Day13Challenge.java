package com.sogeti.codingchallenge.day13;

import com.sogeti.codingchallenge.Challenge;

import java.util.List;

public abstract class Day13Challenge extends Challenge {

    Day13Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201209-input-day9.txt");
    }

    @Override
    protected String solve(List<String> inputList) {


        return getMessage(0);
    }




    abstract String getMessage(long value);
}

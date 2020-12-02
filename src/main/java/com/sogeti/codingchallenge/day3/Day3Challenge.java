package com.sogeti.codingchallenge.day3;

import com.sogeti.codingchallenge.Challenge;
import com.sogeti.codingchallenge.IOController1;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public abstract class Day3Challenge extends Challenge {

    private static final Logger LOGGER = LogManager.getLogger(Day3Challenge.class);

    public Day3Challenge(String challengeTitle, String description) {
        super(challengeTitle, description);
    }

    @Override
    protected List<String> loadInputList() {
        return new IOController1().readInputFileByLine("20201203-input-day3.txt");
    }

    @Override
    protected void solve(List<String> inputList) {

    }


    public void printResult() {

    }

}

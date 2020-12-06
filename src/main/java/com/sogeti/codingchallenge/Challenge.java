package com.sogeti.codingchallenge;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public abstract class Challenge {

    private static final Logger LOGGER = LogManager.getLogger(Challenge.class);
    protected static final String DOTTED_LINE = "-------------------------------------------------------------------";

    private final String title;
    private final String description;
    private long solveTime = 0;

    protected Challenge(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void solveChallenge() {
        LOGGER.info(String.format("%s%n%s", title, DOTTED_LINE));
        LOGGER.info(String.format("Challenge description: %s%n%s", description, DOTTED_LINE));
        List<String> inputList = loadInputList();
        long startTime = System.nanoTime();
        solve(inputList);
        long endTime = System.nanoTime();
        LOGGER.info("Answer:");
        printResult();
        solveTime = endTime - startTime;
        String message = String.format("Solved in %5.5f ms%n", solveTime / 1e6);
        LOGGER.info(message + DOTTED_LINE);
    }

    protected abstract void solve(List<String> inputList);

    protected abstract List<String> loadInputList();

    protected abstract void printResult();

    public long getSolveTime() {
        return solveTime;
    }
}

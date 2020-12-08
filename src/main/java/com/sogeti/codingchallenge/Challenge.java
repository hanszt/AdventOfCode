package com.sogeti.codingchallenge;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public abstract class Challenge {

    protected static final String DOTTED_LINE = "-------------------------------------------------------------------";
    private static final Logger LOGGER = LogManager.getLogger(Challenge.class);
    private final String title;
    private final String description;
    private final String inputFileName;
    private long solveTime = 0;

    protected Challenge(String title, String description, String inputFileName) {
        this.title = title;
        this.description = description;
        this.inputFileName = inputFileName;
    }

    public void solveChallenge() {
        LOGGER.info(String.format("%s%n%s", title, DOTTED_LINE));
        LOGGER.info(String.format("Challenge description: %s%n%s", description, DOTTED_LINE));
        List<String> inputList = loadInputList();
        long startTime = System.nanoTime();
        String result = solve(inputList);
        long endTime = System.nanoTime();
        LOGGER.info("Answer:");
        logMessage(result);
        solveTime = endTime - startTime;
        String message = String.format("Solved in %5.5f ms%n", solveTime / 1e6);
        LOGGER.info(message + DOTTED_LINE);
    }

    protected List<String> loadInputList() {
        return new IOController1().readInputFileByLine(inputFileName);
    }

    protected abstract String solve(List<String> inputList);

    protected void logMessage(String result) {
        LOGGER.info(result);
    }

    public long getSolveTime() {
        return solveTime;
    }
}

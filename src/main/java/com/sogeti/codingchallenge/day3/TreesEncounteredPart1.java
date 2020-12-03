package com.sogeti.codingchallenge.day3;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.awt.*;
import java.util.List;

public class TreesEncounteredPart1 extends Day3Challenge {

    private static final Logger LOGGER = LogManager.getLogger(TreesEncounteredPart1.class);

    private int result = 0;

    public TreesEncounteredPart1() {
        super("Coding challenge day 3 Toboggan trajectory part 1", "Find the number of trees crossed");
    }

    @Override
    protected void calculateResult(List<List<Character>> grid) {
        result = calculateNumberOfTreesEncountered(grid, new Point(0, 0), Path.SLOPE3_1.getSlope());
    }

    @Override
    public void printResult() {
        LOGGER.info("The number of trees crossed is: " + result);
    }

}

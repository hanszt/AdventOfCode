package com.sogeti.codingchallenge.day3;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.awt.*;
import java.util.List;

public class TreesEncounteredPart2 extends Day3Challenge {


    private static final Logger LOGGER = LogManager.getLogger(TreesEncounteredPart2.class);


    public TreesEncounteredPart2() {
        super("Finding trees day 3 part 2", "Find the product of the number of trees crossed of all the given paths");
    }

    private long result = 0;

    @Override
    protected void calculateResult(List<List<Character>> grid) {
        long product = 1;
        for (Path path : Path.values()) {
            int numberOfTrees = calculateNumberOfTreesEncountered(grid, new Point(0, 0), path.getSlope());
            LOGGER.info(String.format("The number of trees crossed using %s is %d", path.name(), numberOfTrees));
            product *= numberOfTrees;
        }
        LOGGER.info("");
        result = product;
    }

    @Override
    public void printResult() {
        LOGGER.info("The product of all the number of trees crossed is: " + result);
    }

}

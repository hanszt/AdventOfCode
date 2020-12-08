package com.sogeti.codingchallenge.day3;

import java.awt.*;
import java.util.List;

public class TreesEncounteredPart1 extends Day3Challenge {

    public TreesEncounteredPart1() {
        super("Counting number of trees crossed part 1", "Find the number of trees crossed");
    }

    @Override
    protected long calculateResult(List<List<Character>> grid) {
        return calculateNumberOfTreesEncountered(grid, new Point(0, 0), Path.SLOPE3_1.getSlope());
    }

    @Override
    public String getMessage(long result) {
        return "The number of trees crossed is: " + result;
    }

}

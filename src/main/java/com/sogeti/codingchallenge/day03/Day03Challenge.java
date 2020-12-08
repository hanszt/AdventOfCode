package com.sogeti.codingchallenge.day03;

import com.sogeti.codingchallenge.Challenge;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Day03Challenge extends Challenge {

    private static final Logger LOGGER = LogManager.getLogger(Day03Challenge.class);
    private static final Character TREE = '#';

    protected Day03Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201203-input-day3.txt");
    }

    @Override
    protected String solve(List<String> inputList) {
        List<List<Character>> grid = !inputList.isEmpty() ? buildGrid(inputList) : Collections.emptyList();
        LOGGER.trace(gridAsString(grid));
        return getMessage(calculateResult(grid));
    }

    protected abstract long calculateResult(List<List<Character>> grid);

    private String gridAsString(List<List<Character>> grid) {
        StringBuilder sb = new StringBuilder();
        for (List<Character> row : grid) {
            sb.append("\n");
            for (Character character : row) {
                sb.append(character);
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    int calculateNumberOfTreesEncountered(List<List<Character>> treeGrid, Point position, Point slope) {
        int numberOfTrees = 0;
        while (position.getY() < treeGrid.size()) {
            LOGGER.trace("x: " + position.x + ", y: " + position.y + ", Is tree: " + treeGrid.get(position.y).get(position.x));
            if (treeGrid.get(position.y).get(position.x).equals(TREE)) {
                numberOfTrees++;
            }
            position.translate(slope.x, slope.y);
        }
        return numberOfTrees;
    }

    private List<List<Character>> buildGrid(List<String> inputList) {
        int patternLength = inputList.get(0).length();
        int height = inputList.size();
        // for each step down, three steps right
        double length = height * (Path.SLOPE7_1.getSlope().getX());
        int timesRepeatedHorizontally = (int) Math.round(length / patternLength);
        List<List<Character>> charGird = new ArrayList<>();
        for (String patternRow : inputList) {
            List<Character> newRow = new ArrayList<>();
            char[] newRowArray = patternRow.repeat(timesRepeatedHorizontally).toCharArray();
            for (char c : newRowArray) {
                newRow.add(c);
            }
            charGird.add(newRow);
        }
        return charGird;
    }

    public abstract String getMessage(long result);

    enum Path {
        SLOPE3_1(new Point(3, 1)),
        SLOPE1_1(new Point(1, 1)),
        SLOPE5_1(new Point(5, 1)),
        SLOPE7_1(new Point(7, 1)),
        SLOPE1_2(new Point(1, 2));

        private final Point slope;

        Path(Point slope) {
            this.slope = slope;
        }

        public Point getSlope() {
            return slope;
        }
    }
}

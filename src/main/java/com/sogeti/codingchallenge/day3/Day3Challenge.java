package com.sogeti.codingchallenge.day3;

import com.sogeti.codingchallenge.Challenge;
import com.sogeti.codingchallenge.IOController1;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Day3Challenge extends Challenge {

    private static final Logger LOGGER = LogManager.getLogger(Day3Challenge.class);

    protected Day3Challenge(String challengeTitle, String description) {
        super(challengeTitle, description);
    }

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

    @Override
    protected List<String> loadInputList() {
        return new IOController1().readInputFileByLine("20201203-input-day3.txt");
    }

    @Override
    protected void solve(List<String> inputList) {
        List<List<Character>> grid = buildGrid(inputList);
        calculateResult(grid);
        LOGGER.trace(gridAsString(grid));
    }

    protected abstract void calculateResult(List<List<Character>> grid);

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


    private static final Character TREE = '#';

    int calculateNumberOfTreesEncountered(List<List<Character>> grid, Point position, Point slope) {
        int numberOfTrees = 0;
        while (position.getY() < grid.size()) {
            LOGGER.trace("x: " + position.x + ", y: " + position.y + ", Is tree: " + grid.get(position.y).get(position.x));
            if (grid.get(position.y).get(position.x).equals(TREE)) {
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
        double length = height * (Path.SLOPE7_1.getSlope().getX() + 1);
        long timesRepeatedHorizontally = Math.round(length / patternLength);
        List<List<Character>> charGird = new ArrayList<>();

        for (String patternRow : inputList) {
            StringBuilder newRowBuilder = new StringBuilder();
            for (int i = 0; i < timesRepeatedHorizontally; i++) {
                newRowBuilder.append(patternRow);
            }
            List<Character> newRow = new ArrayList<>();
            char[] newRowArray = newRowBuilder.toString().toCharArray();
            for (char c : newRowArray) {
                newRow.add(c);
            }
            charGird.add(newRow);
        }
        return charGird;
    }


    public abstract void printResult();

}

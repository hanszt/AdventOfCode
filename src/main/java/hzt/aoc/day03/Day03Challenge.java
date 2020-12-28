package hzt.aoc.day03;

import hzt.aoc.Challenge;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Day03Challenge extends Challenge {

    private static final Character TREE = '#';

    protected Day03Challenge(String part, String description) {
        super(part, description, "20201203-input-day3.txt");
    }

    @Override
    protected String solve(List<String> inputList) {
        List<List<Boolean>> grid = !inputList.isEmpty() ? buildGrid(inputList) : Collections.emptyList();
        LOGGER.trace(booleanGrid2DAsString(grid));
        return getMessage(calculateResult(grid));
    }

    protected abstract long calculateResult(List<List<Boolean>> grid);

    int calculateNumberOfTreesEncountered(List<List<Boolean>> treeGrid, Point position, Point slope) {
        int numberOfTrees = 0;
        while (position.getY() < treeGrid.size()) {
            LOGGER.trace("x: " + position.x + ", y: " + position.y + ", Is tree: " + treeGrid.get(position.y).get(position.x));
            boolean isTree = treeGrid.get(position.y).get(position.x);
            if (isTree) numberOfTrees++;
            position.translate(slope.x, slope.y);
        }
        return numberOfTrees;
    }

    private List<List<Boolean>> buildGrid(List<String> inputList) {
        int patternLength = inputList.get(0).length();
        int height = inputList.size();
        double length = height * (Path.SLOPE7_1.getSlope().getX());
        int timesRepeatedHorizontally = (int) Math.round(length / patternLength);
        List<List<Boolean>> gird = new ArrayList<>();
        for (String patternRow : inputList) {
            List<Boolean> newRow = new ArrayList<>();
            char[] newRowArray = patternRow.repeat(timesRepeatedHorizontally).toCharArray();
            for (Character c : newRowArray) {
                newRow.add(c.equals(TREE));
            }
            gird.add(newRow);
        }
        return gird;
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

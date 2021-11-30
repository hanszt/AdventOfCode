package hzt.aoc.day03;

import java.awt.*;
import java.util.List;

public class TreesEncounteredPart1 extends Day03Challenge {

    public TreesEncounteredPart1() {
        super("part 1",
                "Find the number of trees crossed");
    }

    @Override
    protected long calculateResult(List<List<Boolean>> grid) {
        return calculateNumberOfTreesEncountered(grid, new Point(0, 0), Path.SLOPE3_1.getSlope());
    }

    @Override
    protected String getMessage(String result) {
        return String.format("The number of trees crossed is: %s%n", result);
    }

}

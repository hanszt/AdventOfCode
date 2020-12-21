package hzt.aoc.day03;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.awt.*;
import java.util.List;

public class TreesEncounteredPart2 extends Day03Challenge {


    private static final Logger LOGGER = LogManager.getLogger(TreesEncounteredPart2.class);


    public TreesEncounteredPart2() {
        super("part 2",
                "Find the product of the number of trees crossed by all the given paths");
    }

    @Override
    protected long calculateResult(List<List<Boolean>> grid) {
        long product = 1;
        for (Path path : Path.values()) {
            int numberOfTrees = calculateNumberOfTreesEncountered(grid, new Point(0, 0), path.getSlope());
            LOGGER.info(String.format("The number of trees crossed using %s is %d", path.name(), numberOfTrees));
            product *= numberOfTrees;
        }
        LOGGER.info("");
        return product;
    }

    @Override
    public String getMessage(long result) {
        return String.format("The product of all the number of trees crossed is: %d%n", result);
    }

}

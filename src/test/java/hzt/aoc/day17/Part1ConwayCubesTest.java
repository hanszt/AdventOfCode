package hzt.aoc.day17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class Part1ConwayCubesTest {

    private final Part1ConwayCubes conwayCubes = new Part1ConwayCubes();

    @Test
    void countActiveNeighbors() {
        final int[][] input = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
        };
        final List<List<List<Boolean>>> grid = convertIntArrayToBooleanList(input);
        System.out.println(conwayCubes.grid3DAsString(grid));
        final int neighbours = conwayCubes.countActiveNeighbors(new Point3D(1, 2, 0), grid);
        Assertions.assertEquals(3, neighbours);
    }

    List<List<List<Boolean>>> convertIntArrayToBooleanList(final int[][] input) {
        final List<List<List<Boolean>>> grid3D = new ArrayList<>();
        final List<List<Boolean>> grid2D = new ArrayList<>();
        for (final int[] intRow : input) {
            final List<Boolean> row = new ArrayList<>();
            for (final int value : intRow) {
                row.add(value == 1);
            }
            grid2D.add(row);
        }
        grid3D.add(grid2D);
        return grid3D;
    }
}

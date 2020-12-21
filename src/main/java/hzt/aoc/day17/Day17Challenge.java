package hzt.aoc.day17;

import hzt.aoc.Challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class Day17Challenge extends Challenge {

    Day17Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201217-input-day17.txt");
    }

    static final int NUMBER_OF_CYCLES = 6;
    static final char ACTIVE = '#';

    @Override
    protected String solve(List<String> inputList) {
        return getMessage(solveByGrid(inputList));
    }

    protected abstract long solveByGrid(List<String> inputList);

    List<List<List<Boolean>>> getInitGrid3D(List<String> inputList) {
        List<List<List<Boolean>>> grid3D = new ArrayList<>();
        List<List<Boolean>> grid2D = new ArrayList<>();
        for (String line : inputList) {
            char[] charRow = line.toCharArray();
            List<Boolean> row = new ArrayList<>();
            for (char state : charRow) {
                row.add(state == ACTIVE);
            }
            grid2D.add(row);
        }
        grid3D.add(grid2D);
        return grid3D;
    }

    List<List<List<Boolean>>> copyGrid3D(List<List<List<Boolean>>> grid3d) {
        List<List<List<Boolean>>> copy = new ArrayList<>();
        for (List<List<Boolean>> gridXY : grid3d) {
            List<List<Boolean>> newGridXY = new ArrayList<>();
            for (List<Boolean> rowX : gridXY) {
                List<Boolean> newRowX = new ArrayList<>(rowX);
                newGridXY.add(newRowX);
            }
            copy.add(newGridXY);
        }
        return copy;
    }

    void addInactiveOuterLayer3D(List<List<List<Boolean>>> grid3d) {
        for (List<List<Boolean>> gridXY : grid3d) {
            for (List<Boolean> rowX : gridXY) {
                rowX.add(0, false);
                rowX.add(false);
            }
            gridXY.add(0, createInActiveRow(gridXY.get(0).size()));
            gridXY.add(createInActiveRow(gridXY.get(0).size()));
        }
        int newWidth = grid3d.get(0).get(0).size();
        int newHeight = grid3d.get(0).size();
        grid3d.add(0, createInActiveXYPlane(newWidth, newHeight));
        grid3d.add(createInActiveXYPlane(newWidth, newHeight));
    }

    List<Boolean> createInActiveRow(int width) {
        return IntStream.range(0, width).mapToObj(x -> false).collect(Collectors.toList());
    }

    List<List<Boolean>> createInActiveXYPlane(int width, int height) {
        List<List<Boolean>> inActiveGridXY = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            List<Boolean> row = createInActiveRow(width);
            inActiveGridXY.add(row);
        }
        return inActiveGridXY;
    }

    List<List<List<Boolean>>> createInActiveXYZGrid(int width, int height, int depth) {
        List<List<List<Boolean>>> inActiveGridXYZ = new ArrayList<>();
        for (int z = 0; z < depth; z++) {
            List<List<Boolean>> inActiveGridXY = createInActiveXYPlane(width, height);
            inActiveGridXYZ.add(inActiveGridXY);
        }
        return inActiveGridXYZ;
    }

    String grid3DAsString(List<List<List<Boolean>>> grid3d) {
        StringBuilder sb = new StringBuilder();
        int z = -(grid3d.size() - 1) / 2;
        for (List<List<Boolean>> gridXY : grid3d) {
            sb.append(String.format("%nSlice at z = %d", z));
            sb.append(booleanGrid2DAsString(gridXY));
            z++;
        }
        return sb.toString();
    }



    /*
     If a cube is active and exactly 2 or 3 of its neighbors are also active, the cube remains active. Otherwise, the cube becomes inactive.
     If a cube is inactive but exactly 3 of its neighbors are active, the cube becomes active. Otherwise, the cube remains inactive.
 */
    boolean applyRules(boolean active, int activeNeighbors) {
        if (active && (activeNeighbors == 2 || activeNeighbors == 3)) return true;
        else return !active && activeNeighbors == 3;
    }

    int upperBound(int curVal, int gridDimension) {
        return curVal + 1 < gridDimension ? curVal + 1 : curVal;
    }

    private String getMessage(long global) {
        return String.format("%d cubes are left in the active state after %d cycles", global, NUMBER_OF_CYCLES);
    }
}

package hzt.aoc.day17;

import java.util.ArrayList;
import java.util.List;

public class Part2ConwayCubes extends Part1ConwayCubes {

    public Part2ConwayCubes() {
        super("part 2",
                "Starting with your given initial configuration, simulate six cycles in a 4-dimensional space. " +
                        "How many cubes are left in the active state after the sixth cycle?");
    }

    @Override
    protected long solveByGrid(List<String> inputList) {
        List<List<List<List<Boolean>>>> grid4d = getInitGrid4D(inputList);
        for (int i = 0; i < NUMBER_OF_CYCLES; i++) {
            addInactiveOuterLayer4D(grid4d);
            LOGGER.trace("Iteration: " + i);
            int w = -(grid4d.size() - 1) / 2;
            for (List<List<List<Boolean>>> grid3D : grid4d) {
                LOGGER.trace(String.format("at w = %d %s", w, grid3DAsString(grid3D)));
                w++;
            }
            grid4d = applyRules4D(grid4d);
        }
        return countActive4D(grid4d);
    }

    private long countActive4D(List<List<List<List<Boolean>>>> grid4d) {
        long active = 0;
        for (List<List<List<Boolean>>> grid3D : grid4d) {
            active += countActive3D(grid3D);
        }
        return active;
    }

    private void addInactiveOuterLayer4D(List<List<List<List<Boolean>>>> grid4d) {
        for (List<List<List<Boolean>>> grid3d : grid4d) {
            addInactiveOuterLayer3D(grid3d);
        }
        int width = grid4d.get(0).get(0).get(0).size();
        int height = grid4d.get(0).get(0).size();
        int depth = grid4d.get(0).size();

        grid4d.add(0, createInActiveXYZGrid(width, height, depth));
        grid4d.add(createInActiveXYZGrid(width, height, depth));
    }

    private List<List<List<List<Boolean>>>> getInitGrid4D(List<String> inputList) {
        List<List<List<List<Boolean>>>> grid4D = new ArrayList<>();
        List<List<List<Boolean>>> grid3D = getInitGrid3D(inputList);
        grid4D.add(grid3D);
        return grid4D;
    }

    private List<List<List<List<Boolean>>>> applyRules4D(List<List<List<List<Boolean>>>> grid4d) {
        List<List<List<List<Boolean>>>> newGrid4d = copyGrid4D(grid4d);
        for (int w = 0; w < grid4d.size(); w++) {
            List<List<List<Boolean>>> grid3d = grid4d.get(w);
            for (int z = 0; z < grid3d.size(); z++) {
                List<List<Boolean>> grid2d = grid3d.get(z);
                for (int y = 0; y < grid2d.size(); y++) {
                    List<Boolean> row = grid2d.get(y);
                    for (int x = 0; x < row.size(); x++) {
                        boolean currentActive = row.get(x);
                        int activeNeighbors = countActiveNeighbors(new Point4D(x, y, z, w), grid4d);
                        currentActive = applyRules(currentActive, activeNeighbors);
                        newGrid4d.get(w).get(z).get(y).set(x, currentActive);
                    }
                }
            }
        }
        addInactiveOuterLayer4D(newGrid4d);
        return newGrid4d;
    }

    private List<List<List<List<Boolean>>>> copyGrid4D(List<List<List<List<Boolean>>>> grid4d) {
        List<List<List<List<Boolean>>>> copy = new ArrayList<>();
        for (List<List<List<Boolean>>> grid3d : grid4d) {
            copy.add(copyGrid3D(grid3d));
        }
        return copy;
    }

    private int countActiveNeighbors(Point4D cur, List<List<List<List<Boolean>>>> curGrid4d) {
        int activeNeighbors = 0;
        for (int w = Math.max(cur.getW() - 1, 0); w <= upperBound(cur.getW(), curGrid4d.size()); w++) {
            for (int z = Math.max(cur.getZ() - 1, 0); z <= upperBound(cur.getZ(), curGrid4d.get(0).size()); z++) {
                for (int y = Math.max(cur.getY() - 1, 0); y <= upperBound(cur.getY(), curGrid4d.get(0).get(0).size()); y++) {
                    for (int x = Math.max(cur.getX() - 1, 0); x <= upperBound(cur.getX(), curGrid4d.get(0).get(0).get(0).size()); x++) {
                        if (isActiveNeighbor(new Point4D(x, y, z, w), cur, curGrid4d))
                            activeNeighbors++;
                    }
                }
            }
        }
        return activeNeighbors;
    }

    private boolean isActiveNeighbor(Point4D checked, Point4D cur, List<List<List<List<Boolean>>>> curGrid4d) {
        if (!cur.valueEquals(checked)) {
            return curGrid4d.get(checked.getW()).get(checked.getZ()).get(checked.getY()).get(checked.getX());
        } else return false;
    }

}

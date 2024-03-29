package hzt.aoc.day17;

import java.util.Collection;
import java.util.List;

public class Part1ConwayCubes extends Day17Challenge {

    public Part1ConwayCubes() {
        super("part 1",
                "Starting with your given initial configuration, simulate six cycles. " +
                        "How many cubes are left in the active state after the sixth cycle?");
    }

    public Part1ConwayCubes(final String challengeTitle, final String description) {
        super(challengeTitle, description);
    }

    @Override
    protected long solveByGrid(final List<String> inputList) {
        List<List<List<Boolean>>> grid3d = getInitGrid3D(inputList);
        for (int i = 0; i < NUMBER_OF_CYCLES; i++) {
            addInactiveOuterLayer3D(grid3d);
            LOGGER.trace(String.format("Iteration: %d%n%s", i, grid3DAsString(grid3d)));
            grid3d = updateGrid(grid3d);
        }
        return countActive3D(grid3d);
    }

    long countActive3D(final List<List<List<Boolean>>> grid3d) {
        return grid3d.stream()
                .flatMap(Collection::stream)
                .flatMap(Collection::stream)
                .filter(Boolean.TRUE::equals)
                .count();
    }

    private List<List<List<Boolean>>> updateGrid(final List<List<List<Boolean>>> grid3d) {
        final List<List<List<Boolean>>> newGrid3d = copyGrid3D(grid3d);
        for (int z = 0; z < grid3d.size(); z++) {
            final List<List<Boolean>> grid2d = grid3d.get(z);
            for (int y = 0; y < grid2d.size(); y++) {
                final List<Boolean> row = grid2d.get(y);
                for (int x = 0; x < row.size(); x++) {
                    final int activeNeighbors = countActiveNeighbors(new Point3D(x, y, z), grid3d);
                    final var currentActive = applyRules(row.get(x), activeNeighbors);
                    newGrid3d.get(z).get(y).set(x, currentActive);
                }
            }
        }
        addInactiveOuterLayer3D(newGrid3d);
        return newGrid3d;
    }

    @SuppressWarnings("squid:S134")
    int countActiveNeighbors(final Point3D cur, final List<List<List<Boolean>>> curGrid3d) {
        int activeNeighbors = 0;
        for (int z = Math.max(cur.getZ() - 1, 0); z <= upperBound(cur.getZ(), curGrid3d.size()); z++) {
            for (int y = Math.max(cur.getY() - 1, 0); y <= upperBound(cur.getY(), curGrid3d.get(0).size()); y++) {
                for (int x = Math.max(cur.getX() - 1, 0); x <= upperBound(cur.getX(), curGrid3d.get(0).get(0).size()); x++) {
                    if (isActiveNeighbor(new Point3D(x, y, z), cur, curGrid3d)) {
                        activeNeighbors++;
                    }
                }
            }
        }
        return activeNeighbors;
    }

    private static boolean isActiveNeighbor(final Point3D checked, final Point3D cur, final List<List<List<Boolean>>> curGrid3d) {
        return !cur.equals(checked) && curGrid3d.get(checked.getZ()).get(checked.getY()).get(checked.getX());
    }

}

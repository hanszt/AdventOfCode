package hzt.aoc.day20;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Part1JurassicJigsaw extends Day20Challenge {

    public Part1JurassicJigsaw() {
        super("part 1",
                "Assemble the tiles into an image. What do you get if you multiply together the IDs of the four corner tiles?");
    }

    //TODO: Still has to be solved
    // We only need to find the four corners so the ones that don't match any other tile on 2 edges
    @Override
    protected long calculateAnswer(Map<Integer, List<List<Boolean>>> tileIdsToGrids) {
        long result = 1;
        List<Integer> cornerTileIds = findCornerTileIds(tileIdsToGrids);
        for (Integer id : cornerTileIds) {
            result *= id;
        }
        return result;
    }

    static final int CORNER_TILE_MATCHES = 2;

    private List<Integer> findCornerTileIds(Map<Integer, List<List<Boolean>>> tileIdsToGrids) {
        List<Integer> cornerIds = new ArrayList<>();
        for (Map.Entry<Integer, List<List<Boolean>>> entry : tileIdsToGrids.entrySet()) {
            Integer gridId = entry.getKey();
            List<List<Boolean>> thisGrid = entry.getValue();
            long matches = compareToOtherGrids(thisGrid, tileIdsToGrids);
            if (matches == CORNER_TILE_MATCHES) {
                cornerIds.add(gridId);
            }
        }
        return cornerIds;
    }

    private long compareToOtherGrids(List<List<Boolean>> thisGrid, Map<Integer, List<List<Boolean>>> tileIdsToGrids) {
        return tileIdsToGrids.values().stream().filter(otherGrid -> matchesOtherGridInAnyOrientation(thisGrid, otherGrid)).count();
    }

    private boolean matchesOtherGridInAnyOrientation(List<List<Boolean>> thisGrid, List<List<Boolean>> otherGrid) {
        return false;
    }

    //Rotate Matrix to 90 degree toward Right(clockwise)
    private int[][] rotateMatrixBy90DegreeClockwise(int[][] matrix) {

        int totalRowsOfRotatedMatrix = matrix[0].length; //Total columns of Original Matrix
        int totalColsOfRotatedMatrix = matrix.length; //Total rows of Original Matrix

        int[][] rotatedMatrix = new int[totalRowsOfRotatedMatrix][totalColsOfRotatedMatrix];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                rotatedMatrix[j][(totalColsOfRotatedMatrix - 1) - i] = matrix[i][j];
            }
        }
        return rotatedMatrix;
    }

    private int[][] flipMatrixHorizontally(int[][] matrix) {
        int[][] flippedMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            flippedMatrix[matrix.length - 1 - i] = matrix[i];
        }
        return flippedMatrix;
    }

    @Override
    String getMessage(long global) {
        return String.format("%d", global);
    }

}

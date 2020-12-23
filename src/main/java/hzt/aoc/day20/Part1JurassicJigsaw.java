package hzt.aoc.day20;

import java.util.Map;

// Credits to Johan de Jong
public class Part1JurassicJigsaw extends Day20Challenge {

    public Part1JurassicJigsaw() {
        super("part 1",
                "Assemble the tiles into an image. What do you get if you multiply together the IDs of the four corner tiles?");
    }

    @Override
    protected long calculateAnswer(Map<Integer, Tile> tiles) {
        long result = 1L;
        for (Map.Entry<Integer, Tile> entry : tiles.entrySet()) {
            Tile tile = entry.getValue();
            if (tile.isBorder(tiles)) {
                result *= entry.getKey();
            }
        }
        return result;
    }

    @Override
    String getMessage(long global) {
        return String.format("%d", global);
    }

}

package hzt.aoc.day24;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Part1LobbyLayout extends Day24Challenge {

    public Part1LobbyLayout() {
        super("part 1",
                "Go through the renovation crew's list and determine which tiles they need to flip. " +
                        "After all of the instructions have been followed, how many tiles are left with the black side up?");
    }


    @Override
    protected long calculateResult(List<List<String>> instructionsList) {
        Map<Point, Tile> tileMap = new HashMap<>();
        Tile centerTile = new Tile(new Point(0, 0));
        tileMap.put(centerTile.getPosition(), centerTile);
        for (List<String> instructions : instructionsList) {
            Tile curTile = centerTile;
            for (String instruction : instructions) {
                curTile = curTile.getNeighborByInstruction(instruction, tileMap);
                tileMap.put(curTile.getPosition(), curTile);
            }
            curTile.flip();
        }
        tileMap.values().forEach(LOGGER::trace);
        return countTilesWithBlackSideUp(tileMap.values());
    }

    private long countTilesWithBlackSideUp(Collection<Tile> tiles) {
        return tiles.stream().filter(Tile::isBlackUp).count();
    }

    @Override
    String getMessage(long result) {
        return String.format("%d", result);
    }

}

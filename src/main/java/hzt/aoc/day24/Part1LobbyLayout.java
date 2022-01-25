package hzt.aoc.day24;

import hzt.collections.ListX;
import hzt.collections.MapX;

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
        MapX<Point, Tile> tileMap = buildFloorByInstructions(instructionsList);
        return ListX.of(tileMap.values()).count(Tile::isBlackUp);
    }

    @Override
    String getMessage(long result) {
        return String.format("%d", result);
    }

}

package hzt.aoc.day24;

import hzt.aoc.Challenge;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

import static hzt.aoc.day24.Tile.*;

public abstract class Day24Challenge extends Challenge {

    Day24Challenge(String part, String description) {
        super(part, description, "20201224-input-day24.txt");
    }

    static final Set<String> INSTRUCTION_SET = Set.of(EAST, SOUTH_EAST, SOUTH_WEST, WEST, NORTH_WEST, NORTH_EAST);

    private static final String COMMA_OR_COMMA_WITH_SPACE = "\\s?[, ]\\s?";

    @Override
    protected String solve(List<String> inputList) {
        List<List<String>> instructions = new ArrayList<>();
        for (String line : inputList) {
            List<String> instruction = new ArrayList<>();
            line = line.replace(SOUTH_EAST, " " + SOUTH_EAST + ",");
            line = line.replace(SOUTH_WEST, " " + SOUTH_WEST + ",");
            line = line.replace(NORTH_WEST, " " + NORTH_WEST + ",");
            line = line.replace(NORTH_EAST, " " + NORTH_EAST + ",");
            String[] array = line.split(COMMA_OR_COMMA_WITH_SPACE);
            for (String string : array) {
                if (string.length() != 2 || !INSTRUCTION_SET.contains(string)) {
                    instruction.addAll(string.chars().mapToObj(c -> (char) c).map(String::valueOf).map(String::strip).collect(Collectors.toList()));
                } else if (!string.isBlank()) {
                    instruction.add(string.strip());
                }
            }
            instructions.add(instruction);
        }
        LOGGER.trace(listOfStringListsAsString(instructions));
        return getMessage(calculateResult(instructions));
    }

    Map<Point, Tile> buildFloorByInstructions(List<List<String>> instructionsList) {
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
        return tileMap;
    }

    long countTilesWithBlackSideUp(Collection<Tile> tiles) {
        return tiles.stream().filter(Tile::isBlackUp).count();
    }

    protected abstract long calculateResult(List<List<String>> instructions);


    abstract String getMessage(long value);
}

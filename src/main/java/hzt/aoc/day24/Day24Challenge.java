package hzt.aoc.day24;

import hzt.aoc.Challenge;

import hzt.aoc.Point2D;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static hzt.aoc.day24.Tile.*;

public abstract class Day24Challenge extends Challenge {

    static final Set<String> INSTRUCTION_SET = Set.of(EAST, SOUTH_EAST, SOUTH_WEST, WEST, NORTH_WEST, NORTH_EAST);

    private static final Pattern COMMA_OR_COMMA_WITH_SPACE = Pattern.compile("\\s?[, ]\\s?", Pattern.UNICODE_CHARACTER_CLASS);

    Day24Challenge(final String part, final String description) {
        super(part, description, "20201224-input-day24.txt");
    }

    @Override
    protected String solve(final List<String> inputList) {
        final List<List<String>> instructions = new ArrayList<>();
        for (String line : inputList) {
            final List<String> instruction = new ArrayList<>();
            line = line.replace(SOUTH_EAST, " " + SOUTH_EAST + ",");
            line = line.replace(SOUTH_WEST, " " + SOUTH_WEST + ",");
            line = line.replace(NORTH_WEST, " " + NORTH_WEST + ",");
            line = line.replace(NORTH_EAST, " " + NORTH_EAST + ",");
            final String[] array = COMMA_OR_COMMA_WITH_SPACE.split(line);
            for (final String string : array) {
                if (string.length() != 2 || !INSTRUCTION_SET.contains(string)) {
                    instruction.addAll(string.chars()
                            .mapToObj(Character::toString)
                            .map(String::strip)
                            .collect(Collectors.toList()));
                    continue;
                }
                if (!string.isBlank()) {
                    instruction.add(string.strip());
                }
            }
            instructions.add(instruction);
        }
        LOGGER.trace(listOfStringListsAsString(instructions));
        return getMessage(calculateResult(instructions));
    }

    Map<Point2D, Tile> buildFloorByInstructions(final List<List<String>> instructionsList) {
        final Map<Point2D, Tile> tileMap = new HashMap<>();
        final Tile centerTile = new Tile(new Point2D(0, 0));
        tileMap.put(centerTile.getPosition(), centerTile);
        for (final List<String> instructions : instructionsList) {
            Tile curTile = centerTile;
            for (final String instruction : instructions) {
                curTile = curTile.getNeighborByInstruction(instruction, tileMap);
                tileMap.put(curTile.getPosition(), curTile);
            }
            curTile.flip();
        }
        tileMap.values().forEach(LOGGER::trace);
        return tileMap;
    }

    long countTilesWithBlackSideUp(final Collection<Tile> tiles) {
        return tiles.stream().filter(Tile::isBlackUp).count();
    }

    protected abstract long calculateResult(List<List<String>> instructions);


    abstract String getMessage(long value);
}

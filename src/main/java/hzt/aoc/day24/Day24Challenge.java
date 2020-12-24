package hzt.aoc.day24;

import hzt.aoc.Challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

    protected abstract long calculateResult(List<List<String>> instructions);


    abstract String getMessage(long value);
}

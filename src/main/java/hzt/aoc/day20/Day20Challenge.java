package hzt.aoc.day20;

import hzt.aoc.Challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Day20Challenge extends Challenge {

    Day20Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201220-input-day20ref.txt");
    }

    @Override
    protected String solve(List<String> inputList) {
        final Map<Integer, List<List<Boolean>>> tileIdsToGrids = new HashMap<>();
        List<List<Boolean>> grid = new ArrayList<>();
        int tileId = 0;
        for (String line : inputList) {
            if (line.contains("Tile")) {
                tileId = Integer.parseInt(line.replace("Tile ", "").replace(":", "").strip());
                grid = new ArrayList<>();
            } else if (!line.isEmpty()) {
                List<Boolean> row = line.chars().mapToObj(c -> (char) c).map(c -> c == '#').collect(Collectors.toList());
                grid.add(row);
            }
            if (line.isEmpty()) {
                tileIdsToGrids.put(tileId, grid);
            }
        }
        tileIdsToGrids.forEach((k, v) -> LOGGER.info(k + "->" + booleanGrid2DAsString(v)));
        return getMessage(calculateAnswer(tileIdsToGrids));
    }

    protected abstract long calculateAnswer(Map<Integer, List<List<Boolean>>> tileIdsToGrids);


    abstract String getMessage(long value);
}

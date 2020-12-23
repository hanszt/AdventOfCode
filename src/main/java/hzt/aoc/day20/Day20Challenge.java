package hzt.aoc.day20;

import hzt.aoc.Challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Day20Challenge extends Challenge {

    Day20Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201220-input-day20.txt");
    }

    @Override
    protected String solve(List<String> inputList) {
        final Map<Integer, Tile> tileIdsToGrids = parseInput(inputList);
        return getMessage(calculateAnswer(tileIdsToGrids));
    }

    private Map<Integer, Tile> parseInput(List<String> inputList) {
        final Map<Integer, Tile> tileIdsToGrids = new HashMap<>();
        List<String> tileContent = new ArrayList<>();
        int tileId = 0;
        for (String line : inputList) {
            if (line.contains("Tile")) {
                tileId = Integer.parseInt(line.replace("Tile ", "").replace(":", "").strip());
                tileContent = new ArrayList<>();
            } else if (!line.isEmpty()) {
                tileContent.add(line);
            }
            if (line.isBlank()) {
                tileIdsToGrids.put(tileId, new Tile(tileContent));
            }
        }
        tileIdsToGrids.forEach((k, v) -> LOGGER.trace(k + "->" + v));
        LOGGER.trace(tileIdsToGrids.size());
        return tileIdsToGrids;
    }

    protected abstract long calculateAnswer(Map<Integer, Tile> tileIdsToGrids);


    abstract String getMessage(long value);
}

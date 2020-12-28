package hzt.aoc.day24;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Credits to Johan de Jong
public class Part2LobbyLayout extends Day24Challenge {

    public Part2LobbyLayout() {
        super("part 2",
                "How many tiles will be black after 100 days?");
    }

    private static final int DAYS_OF_EXHIBIT = 100;

    private final Set<Tile> blackTiles = new HashSet<>();

    @Override
    protected long calculateResult(List<List<String>> instructions) {
        Map<Point, Tile> tileMap = buildFloorByInstructions(instructions);
        tileMap.values().stream().filter(Tile::isBlackUp).forEach(blackTiles::add);
        for (int day = 0; day < DAYS_OF_EXHIBIT; day++) {
            simulate();
        }
        return blackTiles.size();
    }

    private void simulate() {
        Set<Tile> active = determineActiveSet();
        Set<Tile> originalBlack = new HashSet<>(blackTiles);
        for (Tile position : active) {
            long blackNeighbours = countBlackNeighbours(originalBlack, position);
            if (originalBlack.contains(position)) {
                if (blackNeighbours == 0 || blackNeighbours > 2) {
                    blackTiles.remove(position);
                }
            } else {
                if (blackNeighbours == 2) {
                    blackTiles.add(position);
                }
            }
        }
    }

    private long countBlackNeighbours(Set<Tile> originalBlack, Tile startTile) {
        return startTile.neighbors().stream()
                .filter(originalBlack::contains)
                .count();
    }

    private Set<Tile> determineActiveSet() {
        Set<Tile> active = new HashSet<>(blackTiles);
        for (Tile position : blackTiles) {
            active.addAll(position.neighbors());
        }
        return active;
    }

    @Override
    String getMessage(long global) {
        return String.format("%d", global);
    }
}

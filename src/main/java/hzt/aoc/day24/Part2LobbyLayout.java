package hzt.aoc.day24;

import hzt.collections.MutableSetX;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Credits to Johan de Jong
public class Part2LobbyLayout extends Day24Challenge {

    private static final int DAYS_OF_EXHIBIT = 100;

    public Part2LobbyLayout() {
        super("part 2",
                "How many tiles will be black after 100 days?");
    }

    @Override
    protected long calculateResult(List<List<String>> instructions) {
        var tileMap = buildFloorByInstructions(instructions);
        var blackTiles = tileMap.values().filterTo(MutableSetX::empty, Tile::isBlackUp);
        for (int day = 0; day < DAYS_OF_EXHIBIT; day++) {
            simulate(blackTiles);
        }
        return blackTiles.size();
    }

    private void simulate(Set<Tile> blackTiles) {
        Set<Tile> active = determineActiveSet(blackTiles);
        var originalBlack = new HashSet<>(blackTiles);
        for (Tile position : active) {
            long blackNeighbours = position.neighbors().count(originalBlack::contains);
            if (originalBlack.contains(position) && (blackNeighbours == 0 || blackNeighbours > 2)) {
                blackTiles.remove(position);
            } else if (blackNeighbours == 2) {
                blackTiles.add(position);
            }
        }
    }

    private Set<Tile> determineActiveSet(Set<Tile> blackTiles) {
        var active = MutableSetX.of(new HashSet<>(blackTiles));
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

package hzt.aoc.day24;

import hzt.collections.ListX;
import hzt.collections.MapX;
import hzt.collections.MutableListX;
import hzt.collections.MutableMapX;

import java.awt.*;
import java.util.List;
import java.util.*;

public class Tile {

    static final String EAST = "e";
    static final String SOUTH_EAST = "se";
    static final String SOUTH_WEST = "sw";
    static final String WEST = "w";
    static final String NORTH_WEST = "nw";
    static final String NORTH_EAST = "ne";

    private static final Map<String, String> OPPOSITE_DIR = new HashMap<>();
    private static final MutableMapX<String, Point> INSTRUCTION_TO_DIR = MutableMapX.empty();

    static {
        OPPOSITE_DIR.put(EAST, WEST);
        OPPOSITE_DIR.put(WEST, EAST);
        OPPOSITE_DIR.put(SOUTH_EAST, NORTH_WEST);
        OPPOSITE_DIR.put(NORTH_WEST, SOUTH_EAST);
        OPPOSITE_DIR.put(SOUTH_WEST, NORTH_EAST);
        OPPOSITE_DIR.put(NORTH_EAST, SOUTH_WEST);
        INSTRUCTION_TO_DIR.put(EAST, new Point(1, 0));
        INSTRUCTION_TO_DIR.put(WEST, new Point(-1, 0));
        INSTRUCTION_TO_DIR.put(SOUTH_EAST, new Point(1, -1));
        INSTRUCTION_TO_DIR.put(NORTH_WEST, new Point(-1, 1));
        INSTRUCTION_TO_DIR.put(SOUTH_WEST, new Point(0, -1));
        INSTRUCTION_TO_DIR.put(NORTH_EAST, new Point(0, 1));
    }

    private final Point position;
    private boolean blackUp;
    private int nrOfBlackNeighbors;

    private final Map<Point, Tile> instructionsToNeighborsMap = new HashMap<>();

    public Tile(Point position) {
        this.position = position;
        this.blackUp = false;
    }

    void flip() {
        blackUp = !blackUp;
    }

    public Tile getNeighborByInstruction(String instruction, Map<Point, Tile> allTiles) {
        Point delta = INSTRUCTION_TO_DIR.get(instruction);
        Point newPosition = new Point(this.position.x + delta.x, this.position.y + delta.y);
        Tile neighbor;
        if (instructionsToNeighborsMap.get(delta) != null) neighbor = instructionsToNeighborsMap.get(delta);
        else if (allTiles.containsKey(newPosition)) neighbor = allTiles.get(newPosition);
        else neighbor = new Tile(newPosition);
        neighbor.instructionsToNeighborsMap.put(INSTRUCTION_TO_DIR.get(OPPOSITE_DIR.get(instruction)), this);
        this.instructionsToNeighborsMap.put(INSTRUCTION_TO_DIR.get(instruction), neighbor);
        return neighbor;
    }

    void countBlackNeighbors() {
        nrOfBlackNeighbors = 0;
        for (Tile neighbor : instructionsToNeighborsMap.values()) {
            if (neighbor.isBlackUp()) nrOfBlackNeighbors++;
        }
    }

    void countBlackNeighbors(Map<Point, Tile> allTiles) {
        nrOfBlackNeighbors = 0;
        for (Point delta : INSTRUCTION_TO_DIR.values()) {
            Point neighborPosition = new Point(this.position.x + delta.x, this.position.y + delta.y);
            if (allTiles.containsKey(neighborPosition)) {
                Tile neighbor = allTiles.get(neighborPosition);
                if (neighbor.isBlackUp()) nrOfBlackNeighbors++;
            }
        }
    }

    //    Any black tile with zero or more than 2 black tiles immediately adjacent to it is flipped to white.
    //    Any white tile with exactly 2 black tiles immediately adjacent to it is flipped to black.
    void executeRule() {
        if (blackUp && (nrOfBlackNeighbors == 0 || nrOfBlackNeighbors > 2)) flip();
        if (!blackUp && nrOfBlackNeighbors == 2) flip();
    }

    MutableListX<Tile> neighbors() {
        return INSTRUCTION_TO_DIR.values()
                        .toMutableListOf(delta -> new Tile(new Point(position.x + delta.x, position.y + delta.y)));
    }

    public boolean isBlackUp() {
        return blackUp;
    }

    public Point getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return Objects.equals(position, tile.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    private String neighborsAsString() {
        StringBuilder sb = new StringBuilder();
        instructionsToNeighborsMap.entrySet().forEach(e -> sb.append(neighborAsString(e)));
        return sb.toString();
    }

    private String neighborAsString(Map.Entry<Point, Tile> e) {
        Point p = e.getValue().position;
        Point delta = e.getKey();
        return String.format("delta(x=%2d, y=%2d)->(position='(x=%3d, y=%3d)', blackUp=%5b) ",
                delta.x, delta.y, p.x, p.y, e.getValue().blackUp);
    }

    @Override
    public String toString() {
        return String.format("Tile{position='(x=%3d, y=%3d)', blackUp=%-5b, nrOfBlackNeighbors=%d, Neighbors={%s}}",
                position.x, position.y, blackUp, nrOfBlackNeighbors, neighborsAsString());
    }
}

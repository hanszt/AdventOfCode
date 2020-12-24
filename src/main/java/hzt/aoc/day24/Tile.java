package hzt.aoc.day24;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Tile {

    static final String EAST = "e";
    static final String SOUTH_EAST = "se";
    static final String SOUTH_WEST = "sw";
    static final String WEST = "w";
    static final String NORTH_WEST = "nw";
    static final String NORTH_EAST = "ne";

    private static final Map<String, String> OPPOSITE_DIR = new HashMap<>();
    private static final Map<String, Point> INSTRUCTION_TO_DIR = new HashMap<>();

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

    private final HashMap<String, Tile> instructionsToNeighborsMap = new HashMap<>();

    public Tile(Point position) {
        this.position = position;
        this.blackUp = false;
    }

    void flip() {
        blackUp = !blackUp;
    }

    public Tile getNeighborByInstruction(String direction, Map<Point, Tile> allTiles) {
        Point delta = INSTRUCTION_TO_DIR.get(direction);
        Point newPosition = new Point(this.position.x + delta.x, this.position.y + delta.y);
        Tile neighbor;
        if (instructionsToNeighborsMap.get(direction) != null) neighbor = instructionsToNeighborsMap.get(direction);
        else if (allTiles.containsKey(newPosition)) neighbor = allTiles.get(newPosition);
        else neighbor = new Tile(newPosition);
        neighbor.instructionsToNeighborsMap.put(OPPOSITE_DIR.get(direction), this);
        this.instructionsToNeighborsMap.put(direction, neighbor);
        return neighbor;
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

    private String neighborAsString(Map.Entry<String, Tile> e) {
        Point p = e.getValue().position;
        return String.format("%2s->position='(x=%3d, y=%3d)', ", e.getKey(), p.x, p.y);
    }

    @Override
    public String toString() {
        return String.format("Tile{position='(x=%3d, y=%3d)', blackUp=%-5b, Neighbors={%s}}",
                position.x, position.y, blackUp, neighborsAsString());
    }
}

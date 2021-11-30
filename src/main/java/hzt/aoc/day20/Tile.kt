package hzt.aoc.day20;

import java.awt.*;
import java.util.*;
import java.util.List;

// Credits to Johan de Jong
public class Tile {

    private Point position;
    private final List<String> content;
    private final List<List<String>> orientations;

    public Tile(List<String> content) {
        this.position = position;
        this.content = content;
        this.orientations = calculateOrientations();
    }

    private List<String> tileSides() {
        List<String> sidesWithoutFlip = new ArrayList<>();
        sidesWithoutFlip.add(content.get(0));
        sidesWithoutFlip.add(content.get(content.size() - 1));
        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();
        for (String line : content) {
            left.append(line.charAt(0));
            right.append(line.charAt(line.length() - 1));
        }
        sidesWithoutFlip.add(left.toString());
        sidesWithoutFlip.add(right.toString());
        List<String> sides = new ArrayList<>();
        for (String side : sidesWithoutFlip) {
            sides.add(side);
            StringBuilder sb = new StringBuilder(side);
            sb.reverse();
            sides.add(sb.toString());
        }
        return sides;
    }

    private static final int CORNERS = 4;

    public boolean isBorder(Map<Integer, Tile> tiles) {
        Set<String> otherTiles = otherTileBorders(tiles);
        Set<String> sideTiles = new HashSet<>(tileSides());
        return countCommonElements(sideTiles, otherTiles) == CORNERS;
    }

    private long countCommonElements(Set<String> sideTiles, Set<String> otherTiles) {
        return sideTiles.stream().filter(otherTiles::contains).count();
    }

    private Set<String> otherTileBorders(Map<Integer, Tile> tiles) {
        Set<String> otherSet = new HashSet<>();
        for (Tile otherTile : tiles.values()) {
            if (!equals(otherTile)) {
                otherSet.addAll(otherTile.tileSides());
            }
        }
        return otherSet;
    }

    private List<List<String>> calculateOrientations() {
        List<List<String>> result = new ArrayList<>();
        collectRotations(result, content);
        collectRotations(result, flip());
        return result;
    }

    private void collectRotations(List<List<String>> result, List<String> input) {
        result.add(input);
        List<String> temp = rotate(input);
        result.add(temp);
        temp = rotate(temp);
        result.add(temp);
        temp = rotate(temp);
        result.add(temp);
    }

    private List<String> flip() {
        List<String> result = new ArrayList<>();
        for (String line : content) {
            StringBuilder sb = new StringBuilder(line);
            sb.reverse();
            result.add(sb.toString());
        }
        return result;
    }

    private List<String> rotate(List<String> original) {
        List<String> result = new ArrayList<>();
        for (int x = 0; x < original.size(); x++) {
            StringBuilder sb = new StringBuilder();
            for (int y = original.size() - 1; y >= 0; y--) {
                sb.append(original.get(y).charAt(x));
            }
            result.add(sb.toString());
        }
        return result;
    }

    private String asString(List<String> content) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%n"));
        content.forEach(s -> sb.append(s).append(String.format("%n")));
        return sb.toString();
    }

    public String orientationsAsString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%n"));
        orientations.forEach(o -> sb.append(asString(o)));
        return sb.toString();
    }

    public String getTop() {
        return content.get(0);
    }

    public String getBottom() {
        return content.get(content.size() - 1);
    }

    public String getLeft() {
        StringBuilder sb = new StringBuilder();
        for (String line : content) {
            sb.append(line.charAt(0));
        }
        return sb.toString();
    }

    public String getRight() {
        StringBuilder sb = new StringBuilder();
        for (String line : content) {
            sb.append(line.charAt(line.length() - 1));
        }
        return sb.toString();
    }

    public List<String> getInner() {
        List<String> result = new ArrayList<>();
        for (int i = 1; i < content.size() - 1; i++) {
            String line = content.get(i);
            result.add(line.substring(1, line.length() - 1));
        }
        return result;
    }

    public List<String> getContent() {
        return content;
    }

    public List<List<String>> getOrientations() {
        return orientations;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "content=" + asString(content) +
                '}';
    }
}

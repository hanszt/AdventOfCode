package hzt.aoc.day20;

import java.awt.*;
import java.util.*;
import java.util.List;

// Credits to Johan de Jong
public class Part2JurassicJigsaw extends Day20Challenge {

    public Part2JurassicJigsaw() {
        super("part 2",
                "Determine how rough the waters are in the sea monsters' habitat by counting the number of # " +
                        "that are not part of a sea monster. How many # are not part of a sea monster?");
    }

    @Override
    protected long calculateAnswer(Map<Integer, Tile> tileIdsToGrids) {
        int pictureSideLength = (int) Math.sqrt(tileIdsToGrids.size());
        long result = 0;
        Set<Integer> placedIds = new HashSet<>();
        Tile[][] placedTile = new Tile[pictureSideLength][pictureSideLength];
        boolean complete = placeNextTile(placedIds, placedTile, 0, 0, tileIdsToGrids);
        if (complete) {
            List<String> fullPicture = buildFullPicture(placedTile, pictureSideLength);
            result = countHowManyHashesNotPartOfSeeMonster(fullPicture);
        }
        return result;
    }

    private long countHowManyHashesNotPartOfSeeMonster(List<String> fullPicture) {
        List<String> seeMonster = createSeeMonster();
        Tile fullPictureTile = new Tile(fullPicture);
        for (List<String> orientation : fullPictureTile.getOrientations()) {
            List<String> markedList = new ArrayList<>(orientation);
            boolean marked = false;
            for (int y = 0; y <= orientation.size() - seeMonster.size(); y++) {
                for (int x = 0; x <= orientation.get(0).length() - seeMonster.get(0).length(); x++) {
                    if (isPatternAt(orientation, seeMonster, x, y)) {
                        markPatternAt(markedList, seeMonster, x, y);
                        marked = true;
                    }
                }
            }
            if (marked) return countHashes(markedList);
        }
        return 0;
    }

    private List<String> buildFullPicture(Tile[][] placedTile, int pictureSideLength) {
        List<String> fullPicture = new ArrayList<>();
        for (int y = 0; y < pictureSideLength; y++) {
            List<List<String>> inners = new ArrayList<>();
            for (int x = 0; x < pictureSideLength; x++) inners.add(placedTile[y][x].getInner());
            for (int j = 0; j < inners.get(0).size(); j++) {
                StringBuilder sb = new StringBuilder();
                for (List<String> inner : inners) sb.append(inner.get(j));
                fullPicture.add(sb.toString());
            }
        }
        return fullPicture;
    }

    private List<String> createSeeMonster() {
        List<String> seeMonster = new ArrayList<>();
        seeMonster.add("                  # ");
        seeMonster.add("#    ##    ##    ###");
        seeMonster.add(" #  #  #  #  #  #   ");
        return seeMonster;
    }

    private boolean isPatternAt(List<String> fullPicture, List<String> pattern, int x, int y) {
        for (int dy = 0; dy < pattern.size(); dy++) {
            for (int dx = 0; dx < pattern.get(0).length(); dx++) {
                boolean matchesHashInPattern = pattern.get(dy).charAt(dx) == '#';
                boolean noMatchPictureCurSpot = fullPicture.get(y + dy).charAt(x + dx) != '#';
                if (matchesHashInPattern && noMatchPictureCurSpot) {
                    return false;
                }
            }
        }
        return true;
    }

    private void markPatternAt(List<String> fullPicture, List<String> pattern, int x, int y) {
        for (int dy = 0; dy < pattern.size(); dy++) {
            for (int dx = 0; dx < pattern.get(0).length(); dx++) {
                if (pattern.get(dy).charAt(dx) == '#') {
                    String originalLine = fullPicture.get(y + dy);
                    int position = x + dx;
                    String newLine = originalLine.substring(0, position) + "O" + originalLine.substring(position + 1);
                    fullPicture.set(y + dy, newLine);
                }
            }
        }
    }

    private int countHashes(List<String> fullPicture) {
        int count = 0;
        for (String line : fullPicture) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '#') {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean placeNextTile(Set<Integer> placedIds, Tile[][] placed, int x, int y, Map<Integer, Tile> tiles) {
        int sideLength = (int) Math.sqrt(tiles.size());
        for (Map.Entry<Integer, Tile> entry : tiles.entrySet()) {
            if (!placedIds.contains(entry.getKey())) {
                Tile tile = entry.getValue();
                placedIds.add(entry.getKey());
                for (List<String> orientation : tile.getOrientations()) {
                    placed[y][x] = new Tile(orientation);
                    if (canPlaceTile(placed, x, y)) {
                       Point next = updateNextPoint(x, y, sideLength);
                        if (next.y == sideLength) return true;
                        if (placeNextTile(placedIds, placed, next.x, next.y, tiles)) return true;
                    }
                    placed[y][x] = null;
                }
                placedIds.remove(entry.getKey());
            }
        }
        return false;
    }

    private Point updateNextPoint(int x, int y, int sideLength) {
        int nx = x + 1;
        int ny = y;
        if (nx == sideLength) {
            nx = 0;
            ny++;
        }
        return new Point(nx, ny);
    }

    private boolean canPlaceTile(Tile[][] placed, int x, int y) {
        if (x > 0 && !placed[y][x - 1].getRight().equals(placed[y][x].getLeft())) {
            return false;
        }
        if (y > 0) {
            return placed[y - 1][x].getBottom().equals(placed[y][x].getTop());
        }
        return true;
    }


    @Override
    String getMessage(long global) {
        return String.format("%d", global);
    }
}

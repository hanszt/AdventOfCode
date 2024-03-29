package hzt.aoc.day20;

import hzt.aoc.Point2D;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Credits to Johan de Jong
public class Part2JurassicJigsaw extends Day20Challenge {

    private int sideLength;

    public Part2JurassicJigsaw() {
        super("part 2",
                "Determine how rough the waters are in the sea monsters' habitat by counting the number of # " +
                        "that are not part of a sea monster. How many # are not part of a sea monster?");
    }

    @Override
    protected long calculateAnswer(final Map<Integer, Tile> tileIdsToGrids) {
        sideLength = (int) Math.sqrt(tileIdsToGrids.size());
        final int pictureSideLength = (int) Math.sqrt(tileIdsToGrids.size());
        final Set<Integer> placedIds = new HashSet<>();
        final Tile[][] placedTiles = new Tile[pictureSideLength][pictureSideLength];
        final boolean complete = placeNextTile(placedIds, placedTiles, new Point2D(0, 0), tileIdsToGrids);
        if (complete) {
            final List<String> fullPicture = buildFullPicture(placedTiles, pictureSideLength);
            return countHowManyHashesNotPartOfSeeMonster(fullPicture);
        }
        return 0L;
    }

    private static long countHowManyHashesNotPartOfSeeMonster(final List<String> fullPicture) {
        final List<String> seeMonster = createSeeMonster();
        final Tile fullPictureTile = new Tile(fullPicture);
        for (final List<String> orientation : fullPictureTile.getOrientations()) {
            final List<String> markedList = new ArrayList<>(orientation);
            final boolean marked = markPatterns(seeMonster, orientation, markedList);
            if (marked) {
                return countHashes(markedList);
            }
        }
        return 0L;
    }

    private static boolean markPatterns(List<String> seeMonster, List<String> orientation, List<String> markedList) {
        boolean marked = false;
        for (int y = 0; y <= orientation.size() - seeMonster.size(); y++) {
            for (int x = 0; x <= orientation.get(0).length() - seeMonster.get(0).length(); x++) {
                if (isPatternAt(orientation, seeMonster, x, y)) {
                    markPatternAt(markedList, seeMonster, x, y);
                    marked = true;
                }
            }
        }
        return marked;
    }

    private static List<String> buildFullPicture(final Tile[][] placedTileGrid,
                                                 final int pictureSideLength) {
        for (Tile[] row : placedTileGrid) {
            for (Tile tile : row) {
                System.out.print(tile.asString());
            }
            System.out.println();
        }
        final List<String> fullPicture = new ArrayList<>();
        for (int y = 0; y < pictureSideLength; y++) {
            final List<List<String>> inners = new ArrayList<>();
            for (int x = 0; x < pictureSideLength; x++) {
                inners.add(placedTileGrid[y][x].getInner());
            }
            for (int j = 0; j < inners.get(0).size(); j++) {
                final StringBuilder sb = new StringBuilder();
                for (final List<String> inner : inners) {
                    sb.append(inner.get(j));
                }
                fullPicture.add(sb.toString());
            }
        }
        return fullPicture;
    }

    private static List<String> createSeeMonster() {
        return List.of(
                "                  # ",
                "#    ##    ##    ###",
                " #  #  #  #  #  #   ");
    }

    private static boolean isPatternAt(final List<String> fullPicture,
                                       final List<String> pattern, final int x, final int y) {
        for (int dy = 0; dy < pattern.size(); dy++) {
            for (int dx = 0; dx < pattern.get(0).length(); dx++) {
                final boolean matchesHashInPattern = pattern.get(dy).charAt(dx) == '#';
                final boolean noMatchPictureCurSpot = fullPicture.get(y + dy).charAt(x + dx) != '#';
                if (matchesHashInPattern && noMatchPictureCurSpot) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void markPatternAt(final List<String> fullPicture, final List<String> pattern, final int x, final int y) {
        for (int dy = 0; dy < pattern.size(); dy++) {
            for (int dx = 0; dx < pattern.get(0).length(); dx++) {
                if (pattern.get(dy).charAt(dx) == '#') {
                    final String originalLine = fullPicture.get(y + dy);
                    final int position = x + dx;
                    final String newLine = originalLine.substring(0, position) + "O" + originalLine.substring(position + 1);
                    fullPicture.set(y + dy, newLine);
                }
            }
        }
    }

    private static long countHashes(final List<String> fullPicture) {
        return fullPicture.stream()
                .flatMapToInt(String::chars)
                .filter(c -> c == '#')
                .count();
    }

    private boolean placeNextTile(final Set<Integer> placedIds,
                                  final Tile[][] placed,
                                  final Point2D curPosition,
                                  final Map<Integer, Tile> tiles) {
        for (final Map.Entry<Integer, Tile> entry : tiles.entrySet()) {
            if (!placedIds.contains(entry.getKey())) {
                final Tile curTile = entry.getValue();
                curTile.setPosition(curPosition);
                placedIds.add(entry.getKey());
                final var orientationFits = orientationFits(placed, curTile, placedIds, tiles);
                if (orientationFits) {
                    return true;
                }
                placedIds.remove(entry.getKey());
            }
        }
        return false;
    }

    private boolean orientationFits(final Tile[][] placed,
                                    final Tile curTile,
                                    final Set<Integer> placedIds,
                                    final Map<Integer, Tile> tiles) {
        for (final List<String> orientation : curTile.getOrientations()) {
            final Point2D cur = curTile.getPosition();
            placed[cur.getY()][cur.getX()] = new Tile(orientation);
            if (canPlaceTile(placed, cur)) {
                final Point2D next = nextPosition(cur);
                final boolean allTilesPlaced = next.getY() == sideLength;
                final var nextTilePlaced = placeNextTile(placedIds, placed, next, tiles);
                if (allTilesPlaced || nextTilePlaced) {
                    return true;
                }
            }
        }
        return false;
    }

    private Point2D nextPosition(final Point2D point2D) {
        int nextX = point2D.getX() + 1;
        int nextY = point2D.getY();
        if (nextX == sideLength) {
            nextX = 0;
            nextY++;
        }
        return new Point2D(nextX, nextY);
    }

    private static boolean canPlaceTile(final Tile[][] placed, final Point2D p) {
        if (p.getX() > 0 && !placed[p.getY()][p.getX() - 1].getRight().equals(placed[p.getY()][p.getX()].getLeft())) {
            return false;
        }
        if (p.getY() > 0) {
            return placed[p.getY() - 1][p.getX()].getBottom().equals(placed[p.getY()][p.getX()].getTop());
        }
        return true;
    }


    @Override
    String getMessage(final long global) {
        return String.format("%d", global);
    }
}

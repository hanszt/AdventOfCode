package hzt.aoc.day20;

import hzt.collections.MutableListX;
import hzt.strings.StringX;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TileTest {

    @Test
    void testOrientationsWithP() {
        var list = MutableListX.of(
                "#### ",
                "#   #",
                "#### ",
                "#    ",
                "#    ");
        Tile tile = new Tile(list);
        final var orientationsAsString = tile.orientationsAsString();
        System.out.println(orientationsAsString);

        final var oriCount = list.joinToStringX().count(c -> c == '#');
        final var resultCount = StringX.of(orientationsAsString).count(c -> c == '#');
        assertEquals(oriCount * 8, resultCount);
    }

}

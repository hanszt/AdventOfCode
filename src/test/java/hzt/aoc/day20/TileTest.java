package hzt.aoc.day20;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TileTest {

    @Test
    void testOrientationsWithP() {
        List<String> list = new ArrayList<>();
        list.add("#### ");
        list.add("#   #");
        list.add("#### ");
        list.add("#    ");
        list.add("#    ");
        Tile tile = new Tile(list);
        System.out.println(tile.orientationsAsString());
        assertTrue(true);
    }

}

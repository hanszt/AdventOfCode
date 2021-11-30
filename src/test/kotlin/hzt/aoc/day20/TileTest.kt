package hzt.aoc.day20;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        Assertions.assertTrue(true);
    }

}

package hzt.aoc.day20;

import java.util.List;
import java.util.Map;

public class Part2JurassicJigsaw extends Day20Challenge {

    public Part2JurassicJigsaw() {
        super("part 2",
                "");
    }

    //TODO: Still has to be solved
    @Override
    protected long calculateAnswer(Map<Integer, List<List<Boolean>>> tileIdsToGrids) {
        return 0;
    }


    @Override
    String getMessage(long global) {
        return String.format("%d", global);
    }
}

package hzt.aoc.day24;

import java.util.List;

public class Part2LobbyLayout extends Day24Challenge {

    public Part2LobbyLayout() {
        super("part 2",
                "How many tiles will be black after 100 days?");
    }


    @Override
    protected long calculateResult(List<List<String>> instructions) {


        return 0;
    }

    @Override
    String getMessage(long global) {
        return String.format("%d", global);
    }
}

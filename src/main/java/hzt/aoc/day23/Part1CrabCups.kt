package hzt.aoc.day23;

import java.util.List;

public class Part1CrabCups extends Day23Challenge {

    public Part1CrabCups() {
        super("part 1",
                "Using your labeling, simulate 100 moves. What are the labels on the cups after cup 1?");
    }

    private static final int NR_OF_MOVES = 100;
    private static final int CUP_ONE_LABEL = 1;

    @Override
    protected long calculateAnswer(List<Integer> cups) {
        int lowestCupLabel = cups.stream().reduce(Integer::min).orElseThrow();
        int highestCupLabel = cups.stream().reduce(Integer::max).orElseThrow();
        int indexCurrent = 0;
        for (int i = 0; i < NR_OF_MOVES; i++) {
            indexCurrent = makeMove(cups, indexCurrent, lowestCupLabel, highestCupLabel);
        }
        return arrangeInOrder(cups);
    }

    private long arrangeInOrder(List<Integer> cups) {
        int indexCupOne = getIndexByLabel(CUP_ONE_LABEL, cups);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < cups.size() - 1; i++) {
            stringBuilder.append(cups.get((indexCupOne + 1 + i) % cups.size()));
        }
        return Long.parseLong(stringBuilder.toString());
    }

    @Override
    String getMessage(long global) {
        return String.format("%d", global);
    }

}

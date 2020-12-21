package hzt.aoc.day21;

import hzt.aoc.Pair;

import java.util.List;
import java.util.Map;

public class Part2AllergenAssessment extends Day21Challenge {

    public Part2AllergenAssessment() {
        super("part 2",
                "");
    }

    //TODO: Still has to be solved
    @Override
    protected long calculateAnswer(Map<Integer, Pair<List<String>, List<String>>> idsToIngredientsAndAllergens) {
        return 0;
    }

    @Override
    String getMessage(long global) {
        return String.format("%d", global);
    }
}

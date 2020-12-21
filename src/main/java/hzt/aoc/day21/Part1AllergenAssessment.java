package hzt.aoc.day21;

import hzt.aoc.Pair;

import java.util.List;
import java.util.Map;

public class Part1AllergenAssessment extends Day21Challenge {

    public Part1AllergenAssessment() {
        super("part 1",
                "Determine which ingredients cannot possibly contain any of the allergens in your list. " +
                        "How many times do any of those ingredients appear?");
    }


    //TODO: Still has to be solved
    @Override
    protected long calculateAnswer(Map<Integer, Pair<List<String>, List<String>>> idsToIngredientsAndAllergens) {


        for(Pair<List<String>, List<String>> lists : idsToIngredientsAndAllergens.values()) {
            List<String> ingredients = lists.getLeft();
            List<String> allergens = lists.getRight();
            for(Pair<List<String>, List<String>> otherLists : idsToIngredientsAndAllergens.values()) {
                List<String> otherIngredients = otherLists.getLeft();
                List<String> otherAllergens = otherLists.getRight();
            }
            for (String ingredient : ingredients) {

            }
        }
        return 0;
    }

    @Override
    String getMessage(long global) {
        return String.format("%d", global);
    }

}

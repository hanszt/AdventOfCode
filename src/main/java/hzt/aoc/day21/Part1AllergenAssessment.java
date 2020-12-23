package hzt.aoc.day21;

import java.util.*;

// credits to Johan de Jong
public class Part1AllergenAssessment extends Day21Challenge {

    public Part1AllergenAssessment() {
        super("part 1",
                "Determine which ingredients cannot possibly contain any of the allergens in your list. " +
                        "How many times do any of those ingredients appear?");
    }


    @Override
    protected Object calculateAnswer(List<Food> foods) {
        Set<String> allAllergens = extractAllAllergens(foods);
        Set<String> potentialAllergenIngredients = extractPotentialAllergens(allAllergens, foods).getPotentialAllergenIngredients();
        return countIngredientsWithoutAllergens(potentialAllergenIngredients, foods);
    }

    private long countIngredientsWithoutAllergens(Set<String> potentialAllergenIngredients, List<Food> foods) {
        long count = 0;
        for (Food food : foods) {
            for (String ingredient : food.getIngredients()) {
                if (!potentialAllergenIngredients.contains(ingredient)) {
                    count++;
                }
            }
        }
        NavigableMap<String, Integer> navigableMap = new TreeMap<>();
        SortedMap<String, Integer> sortedMap = new TreeMap<>();
        return count;
    }


    @Override
    String getMessage(Object global) {
        return String.format("%s", global);
    }

}

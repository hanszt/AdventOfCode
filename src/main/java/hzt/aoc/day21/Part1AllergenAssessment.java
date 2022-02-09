package hzt.aoc.day21;

import hzt.collections.ListX;
import hzt.collections.MutableSetX;

// credits to Johan de Jong
public class Part1AllergenAssessment extends Day21Challenge {

    public Part1AllergenAssessment() {
        super("part 1",
                "Determine which ingredients cannot possibly contain any of the allergens in your list. " +
                        "How many times do any of those ingredients appear?");
    }

    @Override
    protected String calculateAnswer(ListX<Food> foods) {
        var allAllergens = foods.flatMapTo(MutableSetX::empty, Food::getAllergens);
        var potentialAllergenIngredients = extractAllergens(allAllergens, foods).getLeft();
        final long sum = foods.sumOfLongs(food -> food.getIngredients().count(potentialAllergenIngredients::containsNot));
        return String.valueOf(sum);
    }

    @Override
    protected String getMessage(String global) {
        return String.format("%s", global);
    }

}

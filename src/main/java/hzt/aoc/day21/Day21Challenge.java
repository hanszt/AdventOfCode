package hzt.aoc.day21;

import hzt.aoc.Challenge;
import hzt.aoc.Pair;
import hzt.collections.ListX;
import hzt.collections.MutableSetX;
import hzt.collections.SetX;

import java.util.*;

// credits to Johan de Jong
public abstract class Day21Challenge extends Challenge {

    Day21Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201221-input-day21.txt");
    }

    @Override
    protected String solve(List<String> inputList) {
        return ListX.of(inputList).map(this::parseLine).let(this::calculateAnswer);
    }

    protected abstract String calculateAnswer(ListX<Food> idsToIngredientsAndAllergens);

    private static final String ONE_OR_MORE_SPACES = "\\s+";

    private Food parseLine(String line) {
        String[] ingredientsToAllergens = line.split("contains");
        String[] ingredientsAsArray = ingredientsToAllergens[0]
                .replace("(", "").strip().split(ONE_OR_MORE_SPACES);
        String[] allergensAsArray = ingredientsToAllergens[1].replace(")", "")
                .replace(" ", "").strip().split(",");
        return new Food(Set.of(ingredientsAsArray), Set.of(allergensAsArray));
    }

    Pair<SetX<String>, Map<String, List<String>>> extractAllergens(final Set<String> allAllergens, final ListX<Food> foods) {
        final var potentialAllergenIngredients = MutableSetX.<String>empty();
        final Map<String, List<String>> allergenToIngredientsMap = new HashMap<>();
        for (String allergen : allAllergens) {
            final ListX<Food> foodsWithAllergen = foods.filter(food -> food.getAllergens().contains(allergen));
            final Set<String> allPossibleIngredients = foodsWithAllergen.flatMapTo(MutableSetX::empty, Food::getIngredients);
            for (String ingredient : allPossibleIngredients) {
                boolean inAllFoods = foodsWithAllergen.all(food -> food.getIngredients().contains(ingredient));
                if (inAllFoods) {
                    potentialAllergenIngredients.add(ingredient);
                    allergenToIngredientsMap.computeIfAbsent(allergen, key -> new ArrayList<>()).add(ingredient);
                }
            }
        }
        return new Pair<>(potentialAllergenIngredients, allergenToIngredientsMap);
    }
}

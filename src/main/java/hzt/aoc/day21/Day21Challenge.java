package hzt.aoc.day21;

import hzt.aoc.Challenge;

import java.util.*;
import java.util.stream.Collectors;

// credits to Johan de Jong
public abstract class Day21Challenge extends Challenge {

    Day21Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201221-input-day21.txt");
    }

    @Override
    protected String solve(List<String> inputList) {
        List<Food> foods = inputList.stream().map(this::parseLine).collect(Collectors.toList());
        return getMessage(calculateAnswer(foods));
    }

    protected abstract Object calculateAnswer(List<Food> idsToIngredientsAndAllergens);

    private Food parseLine(String line) {
        String[] ingredientsToAllergens = line.split("contains");
        String[] ingredientsAsArray = ingredientsToAllergens[0].replace("(", "").strip().split("\\s+");
        String[] allergensAsArray = ingredientsToAllergens[1].replace(")", "").replace(" ", "").strip().split(",");
        return new Food(Set.of(ingredientsAsArray), Set.of(allergensAsArray));
    }

    Result extractPotentialAllergens(Set<String> allAllergens, List<Food> foods) {
        Set<String> potentialAllergenIngredients = new HashSet<>();
        Map<String, List<String>> allergenToIngredientsMap = new HashMap<>();
        for (String allergen : allAllergens) {
            List<Food> foodsWithAllergen = extractFoodsWithAllergen(allergen, foods);
            Set<String> allPossibleIngredients = allPossibleIngredientsContainingAllergen(foodsWithAllergen);
            for (String ingredient : allPossibleIngredients) {
                boolean inAllFoods = foodsWithAllergen.stream().allMatch(food -> food.getIngredients().contains(ingredient));
                if (inAllFoods) {
                    potentialAllergenIngredients.add(ingredient);
                    allergenToIngredientsMap.computeIfAbsent(allergen, key -> new ArrayList<>()).add(ingredient);
                }
            }
        }
        return new Result(potentialAllergenIngredients, allergenToIngredientsMap);
    }

    private List<Food> extractFoodsWithAllergen(String allergen, List<Food> foods) {
        return foods.stream()
                .filter(food -> food.getAllergens().contains(allergen))
                .collect(Collectors.toList());
    }

    private Set<String> allPossibleIngredientsContainingAllergen(List<Food> foodsWithAllergen) {
        return foodsWithAllergen.stream()
                .flatMap(food -> food.getIngredients().stream())
                .collect(Collectors.toSet());
    }

    Set<String> extractAllAllergens(List<Food> foods) {
        return foods.stream()
                .flatMap(line -> line.getAllergens().stream())
                .collect(Collectors.toSet());
    }

    abstract String getMessage(Object value);

    static class Result {

        private final Set<String> potentialAllergenIngredients;
        private final Map<String, List<String>> allergenToIngredientsMap;

        public Result(Set<String> potentialAllergenIngredients, Map<String, List<String>> allergenToIngredientsMap) {
            this.potentialAllergenIngredients = potentialAllergenIngredients;
            this.allergenToIngredientsMap = allergenToIngredientsMap;
        }

        public Set<String> getPotentialAllergenIngredients() {
            return Collections.unmodifiableSet(potentialAllergenIngredients);
        }

        public Map<String, List<String>> getAllergenToIngredientsMap() {
            return Collections.unmodifiableMap(allergenToIngredientsMap);
        }
    }
}

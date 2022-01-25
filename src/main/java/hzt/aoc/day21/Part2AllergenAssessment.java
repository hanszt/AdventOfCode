package hzt.aoc.day21;

import hzt.collections.ListX;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// credits to Johan de Jong
public class Part2AllergenAssessment extends Day21Challenge {

    public Part2AllergenAssessment() {
        super("part 2",
                "Time to stock your raft with supplies. What is your canonical dangerous ingredient list?");
    }

    @Override
    protected String calculateAnswer(ListX<Food> foods) {
        var allAllergens = foods.flatMapToMutableSetOf(Food::getAllergens);
        var allergenToIngredientsMap = extractAllergens(allAllergens, foods).getRight();
        return getDangerousIngredientsListAsString(allergenToIngredientsMap);
    }

    private String getDangerousIngredientsListAsString(Map<String, List<String>> allergenToIngredientsMap) {
        Map<String, String> uniqueAllergenToIngredientMap = new TreeMap<>();
        while (uniqueAllergenToIngredientMap.size() < allergenToIngredientsMap.size()) {
            for (Map.Entry<String, List<String>> entry : allergenToIngredientsMap.entrySet()) {
                if (entry.getValue().size() == 1) {
                    String ingredient = entry.getValue().get(0);
                    uniqueAllergenToIngredientMap.put(entry.getKey(), ingredient);
                    for (List<String> ingredients : allergenToIngredientsMap.values()) {
                        ingredients.remove(ingredient);
                    }
                    break;
                }
            }
        }
        return String.join(",", uniqueAllergenToIngredientMap.values());
    }

    @Override
    protected String getMessage(String global) {
        return String.format("%s", global);
    }
}

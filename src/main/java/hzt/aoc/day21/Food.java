package hzt.aoc.day21;

import hzt.collections.MutableSetX;
import hzt.collections.SetX;

import java.util.Collections;
import java.util.Set;

public class Food {

    private final Set<String> ingredients;
    private final Set<String> allergens;

    public Food(Set<String> ingredients, Set<String> allergens) {
        this.ingredients = ingredients;
        this.allergens = allergens;
    }

    public MutableSetX<String> getIngredients() {
        return MutableSetX.of(ingredients);
    }

    public Set<String> getAllergens() {
        return Collections.unmodifiableSet(allergens);
    }

    @Override
    public String toString() {
        return "Food{" +
                "ingredients=" + ingredients +
                ", allergens=" + allergens +
                '}';
    }
}

package hzt.aoc.day21

import java.util.*

class Food(private val ingredients: Set<String>, private val allergens: Set<String>) {
    fun getIngredients(): Set<String> {
        return Collections.unmodifiableSet(ingredients)
    }

    fun getAllergens(): Set<String> {
        return Collections.unmodifiableSet(allergens)
    }

    override fun toString(): String {
        return "Food{" +
                "ingredients=" + ingredients +
                ", allergens=" + allergens +
                '}'
    }
}

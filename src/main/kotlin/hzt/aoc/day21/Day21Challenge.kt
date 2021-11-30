package hzt.aoc.day21

import hzt.aoc.Challenge
import java.util.*
import java.util.stream.Collectors

// credits to Johan de Jong
abstract class Day21Challenge internal constructor(challengeTitle: String, description: String) :
    Challenge(challengeTitle, description, "20201221-input-day21.txt") {
    override fun solve(inputList: List<String>): String {
        val foods = inputList.stream().map { line: String -> parseLine(line) }.collect(Collectors.toList())
        return calculateAnswer(foods)
    }

    protected abstract fun calculateAnswer(foods: List<Food>): String
    private fun parseLine(line: String): Food {
        val ingredientsToAllergens = line.split("contains".toRegex()).toTypedArray()
        val ingredientsAsArray = ingredientsToAllergens[0]
            .replace("(", "").trim().split(ONE_OR_MORE_SPACES.toRegex()).toTypedArray()
        val allergensAsArray = ingredientsToAllergens[1].replace(")", "")
            .replace(" ", "").trim().split(",".toRegex()).toTypedArray()
        return Food(mutableSetOf(*ingredientsAsArray), mutableSetOf(*allergensAsArray))
    }

    fun extractAllergens(allAllergens: Set<String>, foods: List<Food>): Result {
        val potentialAllergenIngredients: MutableSet<String> = HashSet()
        val allergenToIngredientsMap: MutableMap<String, MutableList<String>> = HashMap()
        for (allergen in allAllergens) {
            val foodsWithAllergen = extractFoodsWithAllergen(allergen, foods)
            val allPossibleIngredients = allPossibleIngredientsContainingAllergen(foodsWithAllergen)
            for (ingredient in allPossibleIngredients) {
                val inAllFoods =
                    foodsWithAllergen.stream().allMatch { food: Food -> food.getIngredients().contains(ingredient) }
                if (inAllFoods) {
                    potentialAllergenIngredients.add(ingredient)
                    allergenToIngredientsMap.computeIfAbsent(allergen) { ArrayList() }
                        .add(ingredient)
                }
            }
        }
        return Result(potentialAllergenIngredients, allergenToIngredientsMap)
    }

    private fun extractFoodsWithAllergen(allergen: String, foods: List<Food>): List<Food> {
        return foods.stream()
            .filter { it.getAllergens().contains(allergen) }
            .collect(Collectors.toList())
    }

    private fun allPossibleIngredientsContainingAllergen(foodsWithAllergen: List<Food>): Set<String> {
        return foodsWithAllergen.stream()
            .flatMap { it.getIngredients().stream() }
            .collect(Collectors.toSet())
    }

    fun extractAllAllergens(foods: List<Food>): Set<String> {
        return foods.stream()
            .flatMap { it.getAllergens().stream() }
            .collect(Collectors.toSet())
    }

    class Result(
        private val potentialAllergenIngredients: Set<String>,
        private val allergenToIngredientsMap: Map<String, MutableList<String>>
    ) {
        fun getPotentialAllergenIngredients(): Set<String> {
            return Collections.unmodifiableSet(potentialAllergenIngredients)
        }

        fun getAllergenToIngredientsMap(): Map<String, MutableList<String>> {
            return Collections.unmodifiableMap(
                allergenToIngredientsMap
            )
        }
    }

    companion object {
        private const val ONE_OR_MORE_SPACES = "\\s+"
    }
}

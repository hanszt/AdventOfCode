package hzt.aoc.day21

import hzt.aoc.Challenge
import java.util.*

// credits to Johan de Jong
abstract class Day21Challenge internal constructor(challengeTitle: String, description: String) :
    Challenge(challengeTitle, description, "20201221-input-day21.txt") {

    override fun solve(inputList: List<String>): String = calculateAnswer(inputList.map(::toParsedLine))

    protected abstract fun calculateAnswer(foods: List<Food>): String
    private fun toParsedLine(line: String): Food {
        val ingredientsToAllergens = line.split("contains".toRegex()).toTypedArray()
        val ingredientsAsArray = ingredientsToAllergens[0]
            .replace("(", "").trim()
            .split(ONE_OR_MORE_SPACES.toRegex()).toTypedArray()
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
                    foodsWithAllergen.all { it.getIngredients().contains(ingredient) }
                if (inAllFoods) {
                    potentialAllergenIngredients.add(ingredient)
                    allergenToIngredientsMap.computeIfAbsent(allergen) { ArrayList() }
                        .add(ingredient)
                }
            }
        }
        return Result(potentialAllergenIngredients, allergenToIngredientsMap)
    }

    private fun extractFoodsWithAllergen(allergen: String, foods: List<Food>): List<Food> = foods.asSequence()
        .filter { it.getAllergens().contains(allergen) }
        .toList()

    private fun allPossibleIngredientsContainingAllergen(foodsWithAllergen: List<Food>): Set<String> =
        foodsWithAllergen.asSequence()
            .flatMap { it.getIngredients().asSequence() }
            .toSet()

    fun extractAllAllergens(foods: List<Food>): Set<String> = foods.asSequence()
        .flatMap { it.getAllergens().asSequence() }
        .toSet()

    class Result(
        private val potentialAllergenIngredients: Set<String>,
        private val allergenToIngredientsMap: Map<String, MutableList<String>>
    ) {
        fun getPotentialAllergenIngredients(): Set<String> = Collections.unmodifiableSet(potentialAllergenIngredients)

        fun getAllergenToIngredientsMap(): Map<String, MutableList<String>> = Collections.unmodifiableMap(
            allergenToIngredientsMap
        )
    }

    companion object {
        private const val ONE_OR_MORE_SPACES = "\\s+"
    }
}

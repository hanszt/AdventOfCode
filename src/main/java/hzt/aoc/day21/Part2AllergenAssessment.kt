package hzt.aoc.day21

import java.util.TreeMap

// credits to Johan de Jong
class Part2AllergenAssessment : Day21Challenge(
    "part 2",
    "Time to stock your raft with supplies. What is your canonical dangerous ingredient list"
) {
    override fun calculateAnswer(foods: List<Food>): String {
        val allAllergens = extractAllAllergens(foods)
        val allergenToIngredientsMap = extractAllergens(allAllergens, foods).getAllergenToIngredientsMap()
        return getDangerousIngredientsListAsString(allergenToIngredientsMap)
    }

    private fun getDangerousIngredientsListAsString(allergenToIngredientsMap: Map<String, MutableList<String>>): String {
        val uniqueAllergenToIngredientMap: MutableMap<String, String> = TreeMap()
        while (uniqueAllergenToIngredientMap.size < allergenToIngredientsMap.size) {
            for ((key, value) in allergenToIngredientsMap) {
                if (value.size == 1) {
                    val ingredient = value[0]
                    uniqueAllergenToIngredientMap[key] = ingredient
                    for (ingredients in allergenToIngredientsMap.values) {
                        ingredients.remove(ingredient)
                    }
                    break
                }
            }
        }
        return java.lang.String.join(",", uniqueAllergenToIngredientMap.values)
    }

    override fun getMessage(result: String): String {
        return String.format("%s", result)
    }
}

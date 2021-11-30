package hzt.aoc.day21

// credits to Johan de Jong
class Part1AllergenAssessment : Day21Challenge(
    "part 1",
    "Determine which ingredients cannot possibly contain any of the allergens in your list. " +
            "How many times do any of those ingredients appear"
) {
    override fun calculateAnswer(foods: List<Food>): String {
        val allAllergens = extractAllAllergens(foods)
        val potentialAllergenIngredients = extractAllergens(allAllergens, foods)
            .getPotentialAllergenIngredients()
        return countIngredientsWithoutAllergens(potentialAllergenIngredients, foods).toString()
    }

    private fun countIngredientsWithoutAllergens(potentialAllergenIngredients: Set<String>, foods: List<Food>): Long =
        foods.flatMap { it.getIngredients() }.count { potentialAllergenIngredients.contains(it).not() }.toLong()

    override fun getMessage(result: String): String = String.format("%s", result)
}

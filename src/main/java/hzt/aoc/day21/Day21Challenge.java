package hzt.aoc.day21;

import hzt.aoc.Challenge;
import hzt.aoc.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Day21Challenge extends Challenge {

    Day21Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201221-input-day21ref.txt");
    }

    @Override
    protected String solve(List<String> inputList) {
        Map<Integer, Pair<List<String>, List<String>>> idsToIngredientsAndAllergens = new HashMap<>();
        int counter = 0;
        for (String line : inputList) {
            parseLine(line, idsToIngredientsAndAllergens, counter);
            counter++;
        }
        idsToIngredientsAndAllergens.values().forEach(System.out::println);
        return getMessage(calculateAnswer(idsToIngredientsAndAllergens));
    }

    protected abstract long calculateAnswer(Map<Integer, Pair<List<String>, List<String>>> idsToIngredientsAndAllergens);

    private void parseLine(String line, Map<Integer, Pair<List<String>, List<String>>> idsToIngredientsAndAllergens, int id) {
        String[] ingredientsToAllergens = line.split("contains");
        String[] ingredientsAsArray = ingredientsToAllergens[0].replace("(", "").strip().split("\\s+");
        String[] allergensAsArray = ingredientsToAllergens[1].replace(")", "").replace(" ", "").strip().split(",");
        idsToIngredientsAndAllergens.put(id, new Pair<>(Arrays.asList(ingredientsAsArray), Arrays.asList(allergensAsArray)));
    }


    abstract String getMessage(long value);
}

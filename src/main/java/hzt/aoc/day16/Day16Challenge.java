package hzt.aoc.day16;

import hzt.aoc.Challenge;
import hzt.aoc.Pair;
import hzt.collections.ListX;
import hzt.collections.MutableListX;

import java.util.ArrayList;
import java.util.List;

public abstract class Day16Challenge extends Challenge {

    Day16Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201216-input-day16.txt");
    }

    @Override
    protected String solve(List<String> inputList) {
        var fields = MutableListX.<Field>empty();
        List<Integer> yourTicketValues = new ArrayList<>();
        var nearbyTicketValues = MutableListX.<ListX<Integer>>empty();
        Field.setNext(0);
        int inputPart = 0;
        for (String s : inputList) {
            if (!s.isBlank()) {
                if (s.matches("your ticket:")) inputPart++;
                if (s.matches("nearby tickets:")) inputPart++;

                if (inputPart == 0) addField(s, fields);
                else if (inputPart == 1 && !s.matches("your ticket:")) {
                    yourTicketValues.addAll(commaSeparatedStringToIntegerList(s));
                } else if (inputPart == 2 && !s.matches("nearby tickets:")) {
                    nearbyTicketValues.add(commaSeparatedStringToIntegerList(s));
                }
            }
        }
        return getMessage(solveByParsedInput(fields, yourTicketValues, ListX.of(nearbyTicketValues)));
    }

    protected abstract long solveByParsedInput(ListX<Field> fields, List<Integer> yourTicketValues, ListX<ListX<Integer>> nearbyTicketValues);

    private void addField(String s, List<Field> fields) {
        String[] array = s.split(": ");
        Field field = new Field(array[0]);
        String[] ranges = array[1].split(" or ");
        for (String range : ranges) {
            String[] lowerUpper = range.split("-");
            int lower = Integer.parseInt(lowerUpper[0]);
            int upper = Integer.parseInt(lowerUpper[1]);
            field.addRange(new Pair<>(lower, upper));
        }
        fields.add(field);
    }

    protected List<Integer> findValidTicketValues(ListX<Field> fields, ListX<ListX<Integer>> nearbyTicketValues) {
        return nearbyTicketValues
                .flatMap(ListX::toList)
                .filterTo(MutableListX::empty, value -> fields.any(field -> field.containsValueInRanges(value)));
    }


    abstract String getMessage(long value);
}

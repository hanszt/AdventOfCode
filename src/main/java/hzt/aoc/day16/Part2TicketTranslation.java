package hzt.aoc.day16;

import hzt.collections.ListX;
import hzt.function.It;

import java.util.Arrays;
import java.util.List;

// credits to turkey dev
public class Part2TicketTranslation extends Day16Challenge {

    public Part2TicketTranslation() {
        super("part 2",
                "Once you work out which field is which, look for the six fields on your ticket that start with the word departure. \n" +
                        "What do you get if you multiply those six values together?");
    }

    private static final int FIRST_SIX_FIELDS = 6;

    @Override
    protected long solveByParsedInput(List<Field> fields, List<Integer> ourTicketValues, ListX<ListX<Integer>> nearbyTickets) {
        boolean[][] possibleMatches = new boolean[fields.size()][ourTicketValues.size()];
        for (boolean[] possibleMatch : possibleMatches) {
            Arrays.fill(possibleMatch, true);
        }
        var validTickets = findValidTickets(fields, nearbyTickets);
        for (var ticket : validTickets) {
            for (int col = 0; col < ticket.size(); col++) {
                for (int row = 0; row < fields.size(); row++) {
                    if (!fields.get(row).containsValueInRanges(ticket.get(col))) {
                        possibleMatches[row][col] = false;
                    }
                }
            }
        }
        LOGGER.trace(ourTicketValues);

        iterateUntilUniqueValueForeEachField(possibleMatches);
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(booleanGrid2DAsString(possibleMatches));
        }
        return getAnswer(possibleMatches, ourTicketValues);
    }

    private void iterateUntilUniqueValueForeEachField(boolean[][] possibleMatches) {
        while (!isDone(possibleMatches)) {
            filterOutUniqueValues(possibleMatches);
        }
    }

    public void filterOutUniqueValues(boolean[][] possibleMatches) {
        for (int col = 0; col < possibleMatches[0].length; col++) {
            int count = 0;
            int index = -1;
            for (int row = 0; row < possibleMatches.length; row++) {
                if (possibleMatches[col][row]) {
                    count++;
                    index = row;
                }
            }
            if (count == 1) {
                for (int i = 0; i < possibleMatches.length; i++) {
                    if (i != col) {
                        possibleMatches[i][index] = false;
                    }
                }
            }
        }
    }

    public boolean isDone(boolean[][] possibleMatches) {
        for (boolean[] possibleMatch : possibleMatches) {
            int matches = ListX.ofBools(possibleMatch).count(It::self);
            if (matches > 1) {
                return false;
            }
        }
        return true;
    }

    protected List<ListX<Integer>> findValidTickets(List<Field> fields, ListX<ListX<Integer>> nearbyTickets) {
        return nearbyTickets.filterToMutableList(ticket -> ticket.all(value -> fieldsContainValue(value, fields)));
    }

    private long getAnswer(boolean[][] possibleMatches, List<Integer> ourTicketValues) {
        long answer = 1;
        for (int row = 0; row < FIRST_SIX_FIELDS; row++) {
            for (int col = 0; col < possibleMatches.length; col++) {
                if (possibleMatches[row][col]) {
                    int value = ourTicketValues.get(col);
                    answer *= value;
                    break;
                }
            }
        }
        return answer;
    }

    @Override
    String getMessage(long global) {
        return String.format("%d", global);
    }
}

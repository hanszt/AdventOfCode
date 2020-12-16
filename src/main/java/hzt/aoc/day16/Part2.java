package hzt.aoc.day16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// credits to turkey dev
public class Part2 extends Day16Challenge {

    public Part2() {
        super("part 2",
                "");
    }

    private static final int FIRST_SIX_FIELDS = 6;

    @Override
    protected long solveByParsedInput(List<Field> fields, List<Integer> ourTicketValues, List<List<Integer>> nearbyTickets) {
        boolean[][] possibleMatches = new boolean[fields.size()][ourTicketValues.size()];
        for (boolean[] possibleMatch : possibleMatches) Arrays.fill(possibleMatch, true);
        List<List<Integer>> validTickets = findValidTickets(fields, nearbyTickets);
        for (List<Integer> ticket : validTickets) {
            for (int col = 0; col < ticket.size(); col++) {
                for (int row = 0; row < fields.size(); row++) {
                    if (!fields.get(row).containsValueInRanges(ticket.get(col))) {
                        possibleMatches[row][col] = false;
                    }
                }
            }
        }
        LOGGER.info(ourTicketValues);
        iterateUntilUniqueValueForeEachField(possibleMatches);
        LOGGER.info(getPrintOfPossibleMatches(possibleMatches));
        return getAnswer(possibleMatches, ourTicketValues);
    }

    private void iterateUntilUniqueValueForeEachField(boolean[][] possibleMatches) {
        while (!isDone(possibleMatches)) filterOutUniqueValues(possibleMatches);
    }

    private String getPrintOfPossibleMatches(boolean[][] possibleMatches) {
        StringBuilder sb = new StringBuilder();
        for (boolean[] array : possibleMatches) {
            for (boolean bool : array) {
                sb.append(bool ? 1 : 0).append(" ");
            }
            sb.append(String.format("%n"));
        }
        sb.append(String.format("%n"));
        return sb.toString();
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
            int matches = 0;
            for (boolean match : possibleMatch) {
                if (match) matches++;
            }
            if (matches > 1) return false;
        }
        return true;
    }

    protected List<List<Integer>> findValidTickets(List<Field> fields, List<List<Integer>> nearbyTickets) {
        List<List<Integer>> validTickets = new ArrayList<>();
        for (List<Integer> nearbyTicket : nearbyTickets) {
            boolean containsOnlyValidValues = nearbyTicket.stream().allMatch(value -> fieldsContainValue(value, fields));
            if (containsOnlyValidValues) validTickets.add(nearbyTicket);
        }
        return validTickets;
    }

    private long getAnswer(boolean[][] possibleMatches, List<Integer> ourTicketValues) {
        long answer = 1;
        for (int col = 0; col < FIRST_SIX_FIELDS; col++) {
            for (int row = 0; row < possibleMatches.length; row++) {
                if (possibleMatches[row][col]) {
                    answer *= ourTicketValues.get(row);
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

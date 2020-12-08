package com.sogeti.codingchallenge.day5;

import com.sogeti.codingchallenge.Challenge;
import com.sogeti.codingchallenge.day5.model.Seat;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Day5Challenge extends Challenge {

    static final int NUMBER_OF_ROWS = 128;
    static final int NUMBER_OF_COLUMNS = 8;
    static final char KEEP_LOWER_HALF_ROWS = 'F';
    static final char KEEP_UPPER_HALF_ROWS = 'B';
    static final char KEEP_LOWER_HALF_COLS = 'L';
    static final char KEEP_UPPER_HALF_COLS = 'R';
    static final int AMOUNT_SIGNS_FRONT_BACK = 7;

    protected Day5Challenge(String challengeTitle, String description) {
        super(challengeTitle, description, "20201205-input-day5.txt");
    }

    @Override
    protected String solve(List<String> inputList) {
        List<Seat> seats = inputList.stream().map(this::extractSeat).collect(Collectors.toList());
        int result = calculateResult(seats);
        return getMessage(result);
    }

    protected abstract int calculateResult(List<Seat> seats);

    Seat extractSeat(String string) {
        int lowerBoundRows = 0;
        int upperBoundRows = NUMBER_OF_ROWS;
        int lowerBoundCols = 0;
        int upperBoundCols = NUMBER_OF_COLUMNS;
        for (int i = 0; i < string.length(); i++) {
            if (i < AMOUNT_SIGNS_FRONT_BACK) {
                if (string.charAt(i) == KEEP_UPPER_HALF_ROWS) {
                    lowerBoundRows = newLowerBound(lowerBoundRows, upperBoundRows);
                } else if (string.charAt(i) == KEEP_LOWER_HALF_ROWS) {
                    upperBoundRows = newUpperBound(lowerBoundRows, upperBoundRows);
                }
            } else {
                if (string.charAt(i) == KEEP_UPPER_HALF_COLS) {
                    lowerBoundCols = newLowerBound(lowerBoundCols, upperBoundCols);
                } else if (string.charAt(i) == KEEP_LOWER_HALF_COLS) {
                    upperBoundCols = newUpperBound(lowerBoundCols, upperBoundCols);
                }
            }
        }
        return new Seat(string, lowerBoundRows, lowerBoundCols);
    }

    private int newLowerBound(int lower, int upper) {
        return lower + ((upper - lower) / 2);
    }

    private int newUpperBound(int lower, int upper) {
        return upper - ((upper - lower) / 2);
    }

    int findHighestSeatID(List<Integer> boardingPassIds) {
        return boardingPassIds.stream().reduce(Integer::max).orElseThrow();
    }

    public abstract String getMessage(int result);

}

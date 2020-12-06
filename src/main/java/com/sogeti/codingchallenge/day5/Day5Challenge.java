package com.sogeti.codingchallenge.day5;

import com.sogeti.codingchallenge.Challenge;
import com.sogeti.codingchallenge.IOController2;
import com.sogeti.codingchallenge.day5.model.Seat;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Day5Challenge extends Challenge {

    static final Logger LOGGER = LogManager.getLogger(Day5Challenge.class);
    static final int NUMBER_OF_ROWS = 128;
    static final int NUMBER_OF_COLUMNS = 8;
    static final char KEEP_LOWER_HALF_ROWS = 'F';
    static final char KEEP_UPPER_HALF_ROWS = 'B';
    static final char KEEP_LOWER_HALF_COLS = 'L';
    static final char KEEP_UPPER_HALF_COLS = 'R';
    static final int AMOUNT_SIGNS_FRONT_BACK = 7;

    int highestSeatIdOnBoardingPass = 0;
    final List<Seat> seats = new ArrayList<>();

    protected Day5Challenge(String challengeTitle, String description) {
        super(challengeTitle, description);
    }

    @Override
    protected List<String> loadInputList() {
        return new IOController2().readInputFileByLine("20201205-input-day5.txt");
    }

    @Override
    protected void solve(List<String> inputList) {
        seats.addAll(inputList.stream().map(this::extractSeat).collect(Collectors.toList()));
        calculateResult();
    }

    protected abstract void calculateResult();

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

    void findHighestSeatID(List<Integer> boardingPassIds) {
        highestSeatIdOnBoardingPass = boardingPassIds.stream().reduce(Integer::max).orElseThrow();
    }

    public abstract void printResult();

}

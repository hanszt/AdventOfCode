package com.sogeti.codingchallenge.day5;

import java.util.stream.Collectors;

public class Part1BinaryBoarding extends Day5Challenge {

    public Part1BinaryBoarding() {
        super("Binary boarding pass part 1", "Find the highest seat ID on a boarding pass in the list. ");
    }

    @Override
    protected void calculateResult() {
        findHighestSeatID(seats.stream().map(seat -> seat.getSeatID(NUMBER_OF_COLUMNS)).collect(Collectors.toList()));
    }

    public void printResult() {
        LOGGER.info(String.format("The highest seat ID is: %d", highestSeatIdOnBoardingPass));
    }

}

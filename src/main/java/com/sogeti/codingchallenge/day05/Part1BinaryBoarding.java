package com.sogeti.codingchallenge.day05;

import com.sogeti.codingchallenge.day05.model.Seat;

import java.util.List;
import java.util.stream.Collectors;

public class Part1BinaryBoarding extends Day05Challenge {

    public Part1BinaryBoarding() {
        super("Binary boarding pass part 1", "Find the highest seat ID on a boarding pass in the list. ");
    }

    @Override
    protected int calculateResult(List<Seat> seats) {
        return findHighestSeatID(seats.stream().map(seat -> seat.getSeatID(NUMBER_OF_COLUMNS)).collect(Collectors.toList()));
    }

    @Override
    public String getMessage(int result) {
        return String.format("The highest seat ID is: %d", result);
    }

}

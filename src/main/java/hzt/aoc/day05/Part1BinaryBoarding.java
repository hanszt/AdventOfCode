package hzt.aoc.day05;

import hzt.aoc.day05.model.Seat;
import hzt.collections.ListX;

public class Part1BinaryBoarding extends Day05Challenge {

    public Part1BinaryBoarding() {
        super("part 1", "Find the highest seat ID on a boarding pass in the list. ");
    }

    @Override
    protected int calculateResult(ListX<Seat> seats) {
        return seats.maxOf(seat -> seat.getSeatID(NUMBER_OF_COLUMNS));
    }

    @Override
    protected String getMessage(String result) {
        return String.format("The highest seat ID is: %s%n", result);
    }

}

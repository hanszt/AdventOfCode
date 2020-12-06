package com.sogeti.codingchallenge.day5;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Part2BinaryBoarding extends Day5Challenge {

    public Part2BinaryBoarding() {

        super("Binary boarding pass part 2", "What is your seat Id? See ChallengeDay5.md part 2 for the assignment. ");
    }

    private int mySeatId = 0;

    @Override
    protected void calculateResult() {
        // flight completely full
        Set<Integer> possibleSeatIds = new HashSet<>();
        List<Integer> seatIds = seats.stream().map(seat -> seat.getSeatID(NUMBER_OF_COLUMNS)).collect(Collectors.toList());
        // Every seat id must be unique
        for (Integer seatId : seatIds) {
            // Seats with id one less and one more than myId are in my list
            if (seatIds.contains(seatId - 2)) {
                int possibleSeatId = seatId + 1;
                // My boarding pass is the only missing boarding pass in the list
                if (!seatIds.contains(possibleSeatId)) {
                    possibleSeatIds.add(possibleSeatId);
                }
            }
        }
        findHighestSeatID(seatIds);
        for (Integer seatId : possibleSeatIds) {
            // some of the seats at the very front and back of the plane don't exist on this aircraft, they'll be missing from your list.
            // My seat isn't in the very front or back of the plane
            if (seatId < highestSeatIdOnBoardingPass) {
                mySeatId = seatId;
            }
        }
    }

    @Override
    public void printResult() {
        LOGGER.info(String.format("The id of my seat is: %d", mySeatId));
    }
}

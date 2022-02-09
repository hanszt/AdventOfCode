package hzt.aoc.day05;

import hzt.aoc.day05.model.Seat;
import hzt.collections.ListX;
import hzt.utils.It;

import java.util.HashSet;
import java.util.Set;

public class Part2BinaryBoarding extends Day05Challenge {

    public Part2BinaryBoarding() {

        super("part 2", "What is your seat Id? See ChallengeDay5.md part 2 for the assignment. ");
    }

    @Override
    protected int calculateResult(ListX<Seat> seats) {
        int mySeatId = 0;
        // flight completely full
        Set<Integer> possibleSeatIds = new HashSet<>();
        var seatIds = seats.map(seat -> seat.getSeatID(NUMBER_OF_COLUMNS));
        // Every seat id must be unique
        for (Integer seatId : seatIds) {
            // Seats with id one less and one more than myId are in my list
            if (seatIds.contains(seatId - 2)) {
                int possibleSeatId = seatId + 1;
                // My boarding pass is the only missing boarding pass in the list
                if (seatIds.containsNot(possibleSeatId)) {
                    possibleSeatIds.add(possibleSeatId);
                }
            }
        }
        int highestSeatIdOnBoardingPass = seatIds.maxOf(It::self);
        for (Integer seatId : possibleSeatIds) {
            // some of the seats at the very front and back of the plane don't exist on this aircraft, they'll be missing from your list.
            // My seat isn't in the very front or back of the plane
            if (seatId < highestSeatIdOnBoardingPass) {
                mySeatId = seatId;
            }
        }
        return mySeatId;
    }

    @Override
    protected String getMessage(String mySeatId) {
        return String.format("The id of my seat is: %s%n", mySeatId);
    }
}

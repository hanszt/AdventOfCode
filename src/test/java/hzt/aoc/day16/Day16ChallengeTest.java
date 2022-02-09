package hzt.aoc.day16;

import hzt.aoc.Challenge;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day16ChallengeTest {

    @Test
    void testPart1() {
        final var answer = new Part1TicketTranslation().also(Challenge::solveChallenge).getAnswer();
        assertEquals(32835, Integer.parseInt(answer));
    }

    @Test
    @Disabled("Needs to be fixed")
    void testPart2() {
        final var answer = new Part2TicketTranslation().also(Challenge::solveChallenge).getAnswer();
        assertEquals(514662805187L, Long.parseLong(answer));
    }
}

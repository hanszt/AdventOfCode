package hzt.aoc.day10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10ChallengeTest {

    @Test
    void testPart1() {
        final var challenge = new Part1AdaptorArray();
        challenge.solveChallenge();
        final var answer = challenge.getAnswer();
        assertEquals(2240, Integer.parseInt(answer));
    }

    @Test
    void testPart2() {
        final var challenge = new Part2AdaptorArrayWithCachingLongs();
        challenge.solveChallenge();
        final var answer = challenge.getAnswer();
        assertEquals( 99214346656768L, Long.parseLong(answer));
    }

}

package hzt.aoc.day13;

import hzt.aoc.Challenge;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13ChallengeTest {

    @Test
    void testPart1() {
        final var answer = new Part1ShuttleSearch().also(Challenge::solveChallenge).getAnswer();
        assertEquals(2947, Integer.parseInt(answer));
    }

    @Test
    void testPart2() {
        final var answer = new Part2ShuttleSearch().also(Challenge::solveChallenge).getAnswer();
        assertEquals(526090562196173L, Long.parseLong(answer));
    }
}

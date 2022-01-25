package hzt.aoc.day05;

import hzt.aoc.Challenge;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day05ChallengeTest {

    @Test
    void testPart1() {
        final var answer = new Part1BinaryBoarding()
                .also(Challenge::solveChallenge)
                .run(Challenge::getAnswer)
                .let(Integer::parseInt);

        assertEquals(911, answer);
    }

    @Test
    void testPart2() {
        final var answer = new Part2BinaryBoarding().also(Challenge::solveChallenge).getAnswer();
        assertEquals(629, Integer.parseInt(answer));
    }
}

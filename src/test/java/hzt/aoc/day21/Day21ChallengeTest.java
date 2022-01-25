package hzt.aoc.day21;

import hzt.aoc.Challenge;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day21ChallengeTest {

    @Test
    void testPart1() {
        Challenge challenge = new Part1AllergenAssessment();
        challenge.solveChallenge();
        final var answer = challenge.getAnswer();
        assertEquals(2072, Integer.valueOf(answer));
    }

    @Test
    void testPart2() {
        Challenge challenge = new Part2AllergenAssessment();
        challenge.solveChallenge();
        final var answer = challenge.getAnswer();
        assertEquals("fdsfpg,jmvxx,lkv,cbzcgvc,kfgln,pqqks,pqrvc,lclnj", answer);
    }

}

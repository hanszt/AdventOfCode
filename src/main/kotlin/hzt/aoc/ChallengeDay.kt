package hzt.aoc

import hzt.Launcher
import org.apache.log4j.LogManager
import java.lang.Long.sum
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ChallengeDay(
    val title: String,
    private val textColor: String,
    private val date: LocalDate,
    vararg challenges: Challenge
) {
    private val challenges: Array<out Challenge>

    fun solveChallenges() {
        LOGGER.info(
            String.format(
                "%n%n%s%s%nDay %d: %s%nDate: %s%n%s",
                textColor,
                Launcher.DOTTED_LINE,
                date.dayOfMonth,
                title,
                date.format(DateTimeFormatter.ISO_DATE),
                Launcher.DOTTED_LINE
            )
        )
        for (challenge in challenges) {
            challenge.solveChallenge()
        }
    }

    val solveTime: Long
        get() = sequenceOf(*challenges)
            .map(Challenge::solveTime)
            .reduce(::sum)

    fun challengesAsSequence(): Sequence<Challenge> = sequenceOf(*challenges)

    val dayOfMonth: Int
        get() = date.dayOfMonth

    companion object {
        private val LOGGER = LogManager.getLogger(ChallengeDay::class.java)
    }

    init {
        this.challenges = challenges
        challenges.forEach { it.setTitle(title) }
    }
}

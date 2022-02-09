package hzt.aoc;

import hzt.collections.ArrayX;
import hzt.iterables.IterableX;
import hzt.iterators.ArrayIterator;
import hzt.sequences.Sequence;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.stream.Stream;

import static hzt.Launcher.DOTTED_LINE;

public final class ChallengeDay implements Sequence<Challenge> {

    private static final Logger LOGGER = LogManager.getLogger(ChallengeDay.class);

    private final String textColor;
    private final String title;
    private final LocalDate date;
    private final Challenge[] challenges;

    public ChallengeDay(String title, String textColor, LocalDate date, Challenge... challenges) {
        this.title = title;
        this.textColor = textColor;
        this.date = date;
        this.challenges = challenges;
        ArrayX.of(challenges).forEach(c -> c.setTitle(title));
    }

    public void solveChallenges() {
        LOGGER.info(String.format("%n%n%s%s%nDay %d: %s%nDate: %s%n%s", textColor,
                DOTTED_LINE, date.getDayOfMonth(), title, date.format(DateTimeFormatter.ISO_DATE), DOTTED_LINE));
        for (Challenge challenge : challenges) {
            challenge.solveChallenge();
        }
    }

    public long getSolveTime() {
        return ArrayX.of(challenges).sumOfLongs(Challenge::getSolveTime);
    }

    public String getTitle() {
        return title;
    }

    public Stream<Challenge> challengesAsStream() {
        return Stream.of(challenges);
    }

    public int getDayOfMonth() {
        return date.getDayOfMonth();
    }

    @Override
    public @NotNull Iterator<Challenge> iterator() {
        return ArrayIterator.of(challenges);
    }
}

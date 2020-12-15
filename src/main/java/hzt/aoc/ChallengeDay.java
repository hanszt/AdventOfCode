package hzt.aoc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static hzt.Launcher.DOTTED_LINE;

public class ChallengeDay {

    private static final Logger LOGGER = LogManager.getLogger(ChallengeDay.class);

    private static int next = 0;

    private final int dayNr;
    private final String textColor;
    private final String title;
    private final LocalDate date;
    private final Challenge[] challenges;

    public ChallengeDay(String textColor, String title, LocalDate date, Challenge... challenges) {
        this.dayNr = ++next;
        this.textColor = textColor;
        this.title = title;
        this.date = date;
        this.challenges = challenges;
        for(Challenge c : challenges) c.setTitle(title);
    }

    public void solveChallenges() {
        LOGGER.info(String.format("%n%n%s%s%nDay %d: %s%nDate: %s%n%s", textColor,
                DOTTED_LINE, dayNr, title, date.format(DateTimeFormatter.ISO_DATE), DOTTED_LINE));
        for (Challenge challenge : challenges) {
            challenge.solveChallenge();
        }
    }

    public long getSolveTime() {
        return Arrays.stream(challenges).map(Challenge::getSolveTime).reduce(Long::sum).orElse(0L);
    }

    public Challenge[] getChallenges() {
        return challenges;
    }

    public int getDayNr() {
        return dayNr;
    }
}

package com.sogeti.codingchallenge;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static com.sogeti.codingchallenge.Launcher.LINE_RETURN;

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
    }

    public void solveChallenges() {
        LOGGER.info(String.format("%sDay %d: %s%nDate: %s", textColor, dayNr, title, date.format(DateTimeFormatter.ISO_DATE)));
        LOGGER.info(LINE_RETURN);
        for (Challenge challenge : challenges) {
            challenge.solveChallenge();
            LOGGER.info(LINE_RETURN);
        }
        LOGGER.info(LINE_RETURN);
        LOGGER.info(String.format("%n%n"));
    }

    public long getSolveTime() {
        return Arrays.stream(challenges).map(Challenge::getSolveTime).reduce(Long::sum).orElseThrow();
    }

}

package com.sogeti.codingchallenge;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ChallengeDay {

    private static final Logger LOGGER = LogManager.getLogger(ChallengeDay.class);

    public static final String HASHTAG_LINE_RETURN = String.format("#############################################################################################################################################################%n");

    private static int next = 0;

    private final int dayNr;
    private final String title;
    private final LocalDate date;
    private final Challenge[] challenges;

    public ChallengeDay(String title, LocalDate date, Challenge... challenges) {
        this.dayNr = ++next;
        this.title = title;
        this.date = date;
        this.challenges = challenges;
    }

    public void solveChallenges() {
        try {
            LOGGER.info(String.format("Title Challenge day %d: %s%nDate: %s", dayNr, title, date.format(DateTimeFormatter.ISO_DATE)));
            LOGGER.info(HASHTAG_LINE_RETURN);
            for (Challenge challenge : challenges) {
                challenge.solveChallenge();
                LOGGER.info(HASHTAG_LINE_RETURN);
            }
            LOGGER.info(HASHTAG_LINE_RETURN);
            LOGGER.info(String.format("%n%n"));
        } catch (NullPointerException e) {
            LOGGER.error("No input file found");
            e.printStackTrace();
        }
    }
}

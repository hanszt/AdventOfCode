package com.sogeti.codingchallenge;

import com.sogeti.codingchallenge.day01.ThreeDigitsSumTo2020;
import com.sogeti.codingchallenge.day01.TwoDigitsSumTo2020;
import com.sogeti.codingchallenge.day02.PasswordValidChallenge02;
import com.sogeti.codingchallenge.day02.PasswordValidChallenge1;
import com.sogeti.codingchallenge.day03.TreesEncounteredPart1;
import com.sogeti.codingchallenge.day03.TreesEncounteredPart2;
import com.sogeti.codingchallenge.day04.PassportProcessingPart1;
import com.sogeti.codingchallenge.day04.PassportProcessingPart2;
import com.sogeti.codingchallenge.day05.Part1BinaryBoarding;
import com.sogeti.codingchallenge.day05.Part2BinaryBoarding;
import com.sogeti.codingchallenge.day06.Part1CustomCustoms;
import com.sogeti.codingchallenge.day06.Part2CustomCustoms;
import com.sogeti.codingchallenge.day07.Part1HandyHaversacks;
import com.sogeti.codingchallenge.day07.Part2HandyHaversacks;
import com.sogeti.codingchallenge.day08.Part1HandheldHalting;
import com.sogeti.codingchallenge.day08.Part2HandheldHalting;
import com.sogeti.codingchallenge.day09.Part1EncodingError;
import com.sogeti.codingchallenge.day09.Part2EncodingError;
import com.sogeti.codingchallenge.day10.Part1;
import com.sogeti.codingchallenge.day10.Part2;
import com.sogeti.codingchallenge.view.MainPanelLauncher;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Launcher {

    public static final String LINE_RETURN =
            String.format("___________________________________________________________________%n");
    private static final Logger LOGGER = LogManager.getLogger(Launcher.class);
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_BRIGHT_BLUE = "\u001B[94m";
    private static final String TITTLE = ANSI_RED +
            "   __    ____  _  _  ____  _  _  ____    _____  ____     ___  _____  ____  ____    ___   ___  ___   ___  /\\        \n" +
            "  /__\\  (  _ \\( \\/ )( ___)( \\( )(_  _)  (  _  )( ___)   / __)(  _  )(  _ \\( ___)  (__ \\ / _ \\(__ \\ / _ \\ )(\n" +
            " /(__)\\  )(_) )\\  /  )__)  )  (   )(     )(_)(  )__)   ( (__  )(_)(  )(_) ))__)    / _/( (_) )/ _/( (_) )\\/      \n" +
            "(__)(__)(____/  \\/  (____)(_)\\_) (__)   (_____)(__)     \\___)(_____)(____/(____)  (____)\\___/(____)\\___/ ()    \n" +
            "__________________________________________________________________________________________________________          \n" +
            ANSI_RESET;

    private final List<ChallengeDay> challengeDays = new ArrayList<>();

    public Launcher() {
        challengeDays.add(new ChallengeDay(ANSI_BRIGHT_BLUE, "Report Repair", LocalDate.of(2020, 12, 1),
                new TwoDigitsSumTo2020(), new ThreeDigitsSumTo2020()));
        challengeDays.add(new ChallengeDay(ANSI_GREEN, "Password Philosophy", LocalDate.of(2020, 12, 2),
                new PasswordValidChallenge1(), new PasswordValidChallenge02()));
        challengeDays.add(new ChallengeDay(ANSI_CYAN, "Toboggan Trajectory", LocalDate.of(2020, 12, 3),
                new TreesEncounteredPart1(), new TreesEncounteredPart2()));
        challengeDays.add(new ChallengeDay(ANSI_YELLOW, "Passport Processing", LocalDate.of(2020, 12, 4),
                new PassportProcessingPart1(), new PassportProcessingPart2()));
        challengeDays.add(new ChallengeDay(ANSI_RED, "Binary Boarding", LocalDate.of(2020, 12, 5),
                new Part1BinaryBoarding(), new Part2BinaryBoarding()));
        challengeDays.add(new ChallengeDay(ANSI_BRIGHT_BLUE, "Custom Customs", LocalDate.of(2020, 12, 6),
                new Part1CustomCustoms(), new Part2CustomCustoms()));
        challengeDays.add(new ChallengeDay(ANSI_GREEN, "Handy Haversacks", LocalDate.of(2020, 12, 7),
                new Part1HandyHaversacks(), new Part2HandyHaversacks()));
        challengeDays.add(new ChallengeDay(ANSI_CYAN, "Handheld Halting", LocalDate.of(2020, 12, 8),
                new Part1HandheldHalting(), new Part2HandheldHalting()));
        challengeDays.add(new ChallengeDay(ANSI_YELLOW, "EncodingError", LocalDate.of(2020, 12, 9),
                new Part1EncodingError(), new Part2EncodingError()));
        challengeDays.add(new ChallengeDay(ANSI_RED, "Day 10", LocalDate.of(2020, 12, 10),
                new Part1(), new Part2()));
//        challengeDays.add(new ChallengeDay(ANSI_RED, "", LocalDate.of(2020, 12, 11)));
//        challengeDays.add(new ChallengeDay(ANSI_RED, "", LocalDate.of(2020, 12, 12)));
//        challengeDays.add(new ChallengeDay(ANSI_RED, "", LocalDate.of(2020, 12, 13)));
//        challengeDays.add(new ChallengeDay(ANSI_RED, "", LocalDate.of(2020, 12, 14)));
//        challengeDays.add(new ChallengeDay(ANSI_RED, "", LocalDate.of(2020, 12, 15)));
//        challengeDays.add(new ChallengeDay(ANSI_RED, "", LocalDate.of(2020, 12, 16)));
//        challengeDays.add(new ChallengeDay(ANSI_RED, "", LocalDate.of(2020, 12, 17)));
//        challengeDays.add(new ChallengeDay(ANSI_RED, "", LocalDate.of(2020, 12, 18)));
//        challengeDays.add(new ChallengeDay(ANSI_RED, "", LocalDate.of(2020, 12, 19)));
//        challengeDays.add(new ChallengeDay(ANSI_RED, "", LocalDate.of(2020, 12, 20)));
//        challengeDays.add(new ChallengeDay(ANSI_RED, "", LocalDate.of(2020, 12, 21)));
//        challengeDays.add(new ChallengeDay(ANSI_RED, "", LocalDate.of(2020, 12, 22)));
//        challengeDays.add(new ChallengeDay(ANSI_RED, "", LocalDate.of(2020, 12, 23)));
//        challengeDays.add(new ChallengeDay(ANSI_RED, "", LocalDate.of(2020, 12, 24)));
//        challengeDays.add(new ChallengeDay(ANSI_RED, "", LocalDate.of(2020, 12, 25)));
    }

    public static void main(String[] args) {
        new Launcher().start();
    }

    private void start() {
        LOGGER.info(String.format("%n%s", TITTLE));
        challengeDays.forEach(ChallengeDay::solveChallenges);
        LOGGER.info(String.format("%n%s%sTotal solve time: %.2f seconds%n%s", ANSI_RESET, LINE_RETURN,
                challengeDays.stream().map(ChallengeDay::getSolveTime).reduce(Long::sum).orElseThrow() / 1e9, LINE_RETURN));
        new MainPanelLauncher().demo();

    }

}

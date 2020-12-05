package com.sogeti.codingchallenge;

import com.sogeti.codingchallenge.day1.ThreeDigitsSumTo2020;
import com.sogeti.codingchallenge.day1.TwoDigitsSumTo2020;
import com.sogeti.codingchallenge.day2.PasswordValidChallenge1;
import com.sogeti.codingchallenge.day2.PasswordValidChallenge2;
import com.sogeti.codingchallenge.day3.TreesEncounteredPart1;
import com.sogeti.codingchallenge.day3.TreesEncounteredPart2;
import com.sogeti.codingchallenge.day4.PassportProcessingPart1;
import com.sogeti.codingchallenge.day4.PassportProcessingPart2;
import com.sogeti.codingchallenge.day5.Part1BinaryBoarding;
import com.sogeti.codingchallenge.day5.Part2BinaryBoarding;
import com.sogeti.codingchallenge.view.MainPanelLauncher;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Launcher {

    private static final Logger LOGGER = LogManager.getLogger(Launcher.class);

    private static final String ANSI_BOLD = "\033[0;1m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BRIGHT_PURPLE = "\u001B[95m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_BRIGHT_RED = "\u001B[91m";
    private static final String ANSI_BRIGHT_GREEN = "\u001B[92m";
    private static final String ANSI_BRIGHT_BLUE = "\u001B[94m";

    public static final String LINE_RETURN =
            String.format("___________________________________________________________________%n");

    private static final String TITTLE = ANSI_RED +
            "   __    ____  _  _  ____  _  _  ____    _____  ____     ___  _____  ____  ____    ___   ___  ___   ___  /\\        \n" +
            "  /__\\  (  _ \\( \\/ )( ___)( \\( )(_  _)  (  _  )( ___)   / __)(  _  )(  _ \\( ___)  (__ \\ / _ \\(__ \\ / _ \\ )(\n" +
            " /(__)\\  )(_) )\\  /  )__)  )  (   )(     )(_)(  )__)   ( (__  )(_)(  )(_) ))__)    / _/( (_) )/ _/( (_) )\\/      \n" +
            "(__)(__)(____/  \\/  (____)(_)\\_) (__)   (_____)(__)     \\___)(_____)(____/(____)  (____)\\___/(____)\\___/ ()    \n" +
            "__________________________________________________________________________________________________________          \n" +
            ANSI_RESET;

    private final List<ChallengeDay> challengeDays = new ArrayList<>();

    public Launcher() {
        challengeDays.add(new ChallengeDay(ANSI_BRIGHT_BLUE, "Sum to 2020", LocalDate.of(2020, 12, 1),
                new TwoDigitsSumTo2020(),
                new ThreeDigitsSumTo2020()));
        challengeDays.add(new ChallengeDay(ANSI_GREEN, "Are the passwords valid?", LocalDate.of(2020, 12, 2),
                new PasswordValidChallenge1(),
                new PasswordValidChallenge2()));
        challengeDays.add(new ChallengeDay(ANSI_YELLOW, "Toboggan Trajectory", LocalDate.of(2020, 12, 3),
                new TreesEncounteredPart1(),
                new TreesEncounteredPart2()));
        challengeDays.add(new ChallengeDay(ANSI_CYAN, "Valid Passports", LocalDate.of(2020, 12, 4),
                new PassportProcessingPart1(),
                new PassportProcessingPart2()));
        challengeDays.add(new ChallengeDay(ANSI_BRIGHT_BLUE, "Binary boarding pass", LocalDate.of(2020, 12, 5),
                new Part1BinaryBoarding(),
                new Part2BinaryBoarding()));
    }

    private void start() {
        LOGGER.info(TITTLE);
        challengeDays.forEach(ChallengeDay::solveChallenges);
        LOGGER.info(String.format("Total solve time: %.2f seconds%n%s",
                challengeDays.stream().map(ChallengeDay::getSolveTime).reduce(Long::sum).orElseThrow() / 1e9, LINE_RETURN));
        new MainPanelLauncher().demo();
    }

    public static void main(String[] args) {
        new Launcher().start();
    }

}

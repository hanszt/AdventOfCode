package hzt.aoc;

import hzt.aoc.day01.ThreeDigitsSumTo2020;
import hzt.aoc.day01.TwoDigitsSumTo2020;
import hzt.aoc.day02.PasswordValidChallenge02;
import hzt.aoc.day02.PasswordValidChallenge1;
import hzt.aoc.day03.TreesEncounteredPart1;
import hzt.aoc.day03.TreesEncounteredPart2;
import hzt.aoc.day04.PassportProcessingPart1;
import hzt.aoc.day04.PassportProcessingPart2;
import hzt.aoc.day05.Part1BinaryBoarding;
import hzt.aoc.day05.Part2BinaryBoarding;
import hzt.aoc.day06.Part1CustomCustoms;
import hzt.aoc.day06.Part2CustomCustoms;
import hzt.aoc.day07.Part1HandyHaversacks;
import hzt.aoc.day07.Part2HandyHaversacks;
import hzt.aoc.day08.Part1HandheldHalting;
import hzt.aoc.day08.Part2HandheldHalting;
import hzt.aoc.day09.Part1EncodingError;
import hzt.aoc.day09.Part2EncodingError;
import hzt.aoc.day10.Part1AdaptorArray;
import hzt.aoc.day10.Part2AdaptorArrayWithCaching;
import hzt.aoc.day10.Part2AdaptorArrayWithCachingLongs;
import hzt.aoc.day11.Part1SeatingSystem;
import hzt.aoc.day11.Part2SeatingSystem;
import hzt.aoc.day12.Part1RainRisk;
import hzt.aoc.day12.Part2RainRisk;
import hzt.aoc.day13.Part1ShuttleSearch;
import hzt.aoc.day13.Part2ShuttleSearch;
import hzt.aoc.view.MainPanelLauncher;
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
        challengeDays.add(new ChallengeDay(ANSI_YELLOW, "Encoding Error", LocalDate.of(2020, 12, 9),
                new Part1EncodingError(), new Part2EncodingError()));
        challengeDays.add(new ChallengeDay(ANSI_RED, "Adaptor Array", LocalDate.of(2020, 12, 10),
                new Part1AdaptorArray(), new Part2AdaptorArrayWithCaching(),
                new Part2AdaptorArrayWithCachingLongs()/*, new Part2AdaptorArrayWithoutCaching()*/));
        challengeDays.add(new ChallengeDay(ANSI_BRIGHT_BLUE, "Seating System", LocalDate.of(2020, 12, 11),
                new Part1SeatingSystem(), new Part2SeatingSystem()));
        challengeDays.add(new ChallengeDay(ANSI_GREEN, "Rain Risk", LocalDate.of(2020, 12, 12),
                new Part1RainRisk(), new Part2RainRisk()));
        challengeDays.add(new ChallengeDay(ANSI_RED, "Shuttle Search", LocalDate.of(2020, 12, 13),
                new Part1ShuttleSearch(), new Part2ShuttleSearch()));
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

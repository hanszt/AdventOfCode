package hzt;

import hzt.aoc.Challenge;
import hzt.aoc.ChallengeDay;
import hzt.aoc.Pair;
import hzt.aoc.day01.Part1ReportRepair;
import hzt.aoc.day01.Part2ReportRepair;
import hzt.aoc.day02.Part1PasswordPhilosophy;
import hzt.aoc.day02.Part2PasswordPhilosophy;
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
import hzt.aoc.day10.Part2AdaptorArrayWithoutCaching;
import hzt.aoc.day11.Part1SeatingSystem;
import hzt.aoc.day11.Part2SeatingSystem;
import hzt.aoc.day12.Part1RainRisk;
import hzt.aoc.day12.Part2RainRisk;
import hzt.aoc.day13.Part1ShuttleSearch;
import hzt.aoc.day13.Part2ShuttleSearch;
import hzt.aoc.day13.Part2ShuttleSearchOwnImpl;
import hzt.aoc.day14.Part1DockingData;
import hzt.aoc.day14.Part2DockingData;
import hzt.aoc.day15.Part1RambunctiousRecitation;
import hzt.aoc.day15.Part2RambunctiousRecitation;
import hzt.aoc.day16.Part1TicketTranslation;
import hzt.aoc.day16.Part2TicketTranslation;
import hzt.aoc.day17.Part1ConwayCubes;
import hzt.aoc.day17.Part2ConwayCubes;
import hzt.aoc.day18.Part1OperationOrder;
import hzt.aoc.day18.Part2OperationOrder;
import hzt.aoc.day19.Part1MonsterMessages;
import hzt.aoc.day19.Part2MonsterMessages;
import hzt.aoc.day20.Part1JurassicJigsaw;
import hzt.aoc.day20.Part2JurassicJigsaw;
import hzt.aoc.day21.Part1;
import hzt.aoc.day21.Part2;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.*;

import static java.lang.System.* ;

public class Launcher {

    public static final String DOTTED_LINE = "___________________________________________________________________";
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

    private final Map<Integer, ChallengeDay> challengeDays = new HashMap<>();

    public Launcher() {
        int counter = 1;
        challengeDays.put(counter++, new ChallengeDay(ANSI_BRIGHT_BLUE, "Report Repair", LocalDate.of(2020, 12, 1),
                new Part1ReportRepair(), new Part2ReportRepair()));
        challengeDays.put(counter++, new ChallengeDay(ANSI_GREEN, "Password Philosophy", LocalDate.of(2020, 12, 2),
                new Part1PasswordPhilosophy(), new Part2PasswordPhilosophy()));
        challengeDays.put(counter++, new ChallengeDay(ANSI_CYAN, "Toboggan Trajectory", LocalDate.of(2020, 12, 3),
                new TreesEncounteredPart1(), new TreesEncounteredPart2()));
        challengeDays.put(counter++, new ChallengeDay(ANSI_YELLOW, "Passport Processing", LocalDate.of(2020, 12, 4),
                new PassportProcessingPart1(), new PassportProcessingPart2()));
        challengeDays.put(counter++, new ChallengeDay(ANSI_RED, "Binary Boarding", LocalDate.of(2020, 12, 5),
                new Part1BinaryBoarding(), new Part2BinaryBoarding()));
        challengeDays.put(counter++, new ChallengeDay(ANSI_BRIGHT_BLUE, "Custom Customs", LocalDate.of(2020, 12, 6),
                new Part1CustomCustoms(), new Part2CustomCustoms()));
        challengeDays.put(counter++, new ChallengeDay(ANSI_GREEN, "Handy Haversacks", LocalDate.of(2020, 12, 7),
                new Part1HandyHaversacks(), new Part2HandyHaversacks()));
        challengeDays.put(counter++, new ChallengeDay(ANSI_CYAN, "Handheld Halting", LocalDate.of(2020, 12, 8),
                new Part1HandheldHalting(), new Part2HandheldHalting()));
        challengeDays.put(counter++, new ChallengeDay(ANSI_YELLOW, "Encoding Error", LocalDate.of(2020, 12, 9),
                new Part1EncodingError(), new Part2EncodingError()));
        challengeDays.put(counter++, new ChallengeDay(ANSI_RED, "Adaptor Array", LocalDate.of(2020, 12, 10),
                new Part1AdaptorArray(), new Part2AdaptorArrayWithCaching(),
                new Part2AdaptorArrayWithCachingLongs(), new Part2AdaptorArrayWithoutCaching()));
        challengeDays.put(counter++, new ChallengeDay(ANSI_BRIGHT_BLUE, "Seating System", LocalDate.of(2020, 12, 11),
                new Part1SeatingSystem(), new Part2SeatingSystem()));
        challengeDays.put(counter++, new ChallengeDay(ANSI_GREEN, "Rain Risk", LocalDate.of(2020, 12, 12),
                new Part1RainRisk(), new Part2RainRisk()));
        challengeDays.put(counter++, new ChallengeDay(ANSI_CYAN, "Shuttle Search", LocalDate.of(2020, 12, 13),
                new Part1ShuttleSearch(), new Part2ShuttleSearchOwnImpl(), new Part2ShuttleSearch()));
        challengeDays.put(counter++, new ChallengeDay(ANSI_YELLOW, "Docking Data", LocalDate.of(2020, 12, 14),
                new Part1DockingData(), new Part2DockingData()));
        challengeDays.put(counter++, new ChallengeDay(ANSI_RED, "Rambunctious Recitation", LocalDate.of(2020, 12, 15),
                new Part1RambunctiousRecitation(), new Part2RambunctiousRecitation()));
        challengeDays.put(counter++, new ChallengeDay(ANSI_BRIGHT_BLUE, "Ticket Translation", LocalDate.of(2020, 12, 16),
                new Part1TicketTranslation(), new Part2TicketTranslation()));
        challengeDays.put(counter++, new ChallengeDay(ANSI_GREEN, "ConwayCubes", LocalDate.of(2020, 12, 17),
                new Part1ConwayCubes(), new Part2ConwayCubes()));
        challengeDays.put(counter++, new ChallengeDay(ANSI_CYAN, "Operation Order", LocalDate.of(2020, 12, 18),
                new Part1OperationOrder(), new Part2OperationOrder()));
        challengeDays.put(counter++, new ChallengeDay(ANSI_YELLOW, "Monster Messages", LocalDate.of(2020, 12, 19),
                new Part1MonsterMessages(), new Part2MonsterMessages()));
        challengeDays.put(counter++, new ChallengeDay(ANSI_RED, "Jurassic Jigsaw", LocalDate.of(2020, 12, 20),
                new Part1JurassicJigsaw(), new Part2JurassicJigsaw()));
        challengeDays.put(counter++, new ChallengeDay(ANSI_RED, "", LocalDate.of(2020, 12, 21),
                new Part1(), new Part2()));
        challengeDays.put(counter++, new ChallengeDay(ANSI_RED, "", LocalDate.of(2020, 12, 22)));
        challengeDays.put(counter++, new ChallengeDay(ANSI_RED, "", LocalDate.of(2020, 12, 23)));
        challengeDays.put(counter++, new ChallengeDay(ANSI_RED, "", LocalDate.of(2020, 12, 24)));
        challengeDays.put(counter, new ChallengeDay(ANSI_RED, "", LocalDate.of(2020, 12, 25)));
    }

    public static void main(String[] args) {
        new Launcher().start();
    }

    private void start() {
        String userInput = "";
        long startTime = System.nanoTime();
        LOGGER.info(String.format("%n%s", TITTLE));
        while (!userInput.equals(EXIT)) {
            pressEnterToContinue();
            out.print(menuAsString());
            userInput = execute();
        }
        long runtime = System.nanoTime() - startTime;
        out.printf("%s%nRuntime: %2.3f seconds%n%s%n", ANSI_GREEN, runtime / 1e9, DOTTED_LINE);
        exit(0);
    }

    private void executeAllAndPrintSummary() {
        challengeDays.values().forEach(ChallengeDay::solveChallenges);

        LOGGER.info(sortedSolveTimesAsString(new ArrayList<>(challengeDays.values())));
        LOGGER.info(String.format("%s%nTotal solve time: %.2f seconds%n%s%n",
                DOTTED_LINE, challengeDays.values().stream().map(ChallengeDay::getSolveTime).reduce(Long::sum).orElseThrow() / 1e9,
                DOTTED_LINE));
    }

    private String execute() {
        String input = new Scanner(in).next();
//        String input = "21";
        if (input.equals(EXIT)) return EXIT;
        else if (input.equals(ALL)) executeAllAndPrintSummary();
        else executeByChallengeNumber(input);
        return input;
    }

    private void executeByChallengeNumber(String input) {
        try {
            int key = Integer.parseInt(input);
            if (challengeDays.containsKey(key)) {
                challengeDays.get(key).solveChallenges();
            } else out.println("The selected number is not in the challenge list...");
        } catch (NumberFormatException e) {
            out.println("You didn't choose a valid day number...");
        }
    }

    private static final String EXIT = "e";
    private static final String ALL = "a";

    private String menuAsString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%n"));
        challengeDays.forEach((k, v) -> sb.append(String.format("Press '%2d' to execute day %2d %s.%n", k, k, v.getTitle())));
        sb.append(String.format("Press '%s' to execute all challenges at once.%n", ALL));
        sb.append(String.format("Press '%s' to exit the program.%nYour input: ", EXIT));
        return sb.toString();
    }

    private String sortedSolveTimesAsString(List<ChallengeDay> challengeDays) {
        List<Pair<Challenge, ChallengeDay>> challenges = new ArrayList<>();
        challengeDays.forEach(day -> Arrays.asList(day.getChallenges()).forEach(challenge -> challenges.add(new Pair<>(challenge, day))));
        challenges.sort(Comparator.comparing(c -> c.getLeft().getSolveTime()));

        StringBuilder sb = new StringBuilder();
        sb.append(ANSI_RESET);
        sb.append(String.format("%nChallenges sorted by solve time:%n"));
        for (Pair<Challenge, ChallengeDay> p : challenges) {
            sb.append(String.format("Day %2d Challenge: %-50s, solve time: %8.3f milliseconds%n",
                    p.getRight().getDayOfMonth(),
                    p.getRight().getTitle() + " " + p.getLeft().getNote(),
                    p.getLeft().getSolveTime() / 1e6));
        }
        return sb.toString();
    }

    private static void pressEnterToContinue() {
        out.printf("%n%sPress Enter key to continue...%s", ANSI_GREEN, ANSI_RESET);
        new Scanner(in).nextLine();
    }

}

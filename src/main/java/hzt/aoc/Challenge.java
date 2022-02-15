package hzt.aoc;

import hzt.aoc.io.IOController2;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static hzt.Launcher.DOTTED_LINE;

public abstract class Challenge {

    public static final Logger LOGGER = LogManager.getLogger(Challenge.class);
    protected static final Pattern NUMBER_LENGTH_ONE_OR_MORE = Pattern.compile("\\d+");
    protected static final Pattern NOT_DIGIT_LENGTH_ONE_OR_MORE = Pattern.compile("\\D+");

    private String title;
    private final String part;
    private final String description;
    private final String inputFileName;
    private long solveTime = 0;
    private ZonedDateTime startTimeSolve;
    private String answer;

    protected Challenge(String part, String description, String inputFileName) {
        this.part = part;
        this.description = description;
        this.inputFileName = inputFileName;
    }

    public void solveChallenge() {
        LOGGER.info(String.format("%n%s %s%nInput: %s%nChallenge: %s%n%s",
                title, part, inputFileName, description, DOTTED_LINE));
        List<String> inputList = loadInputList();
        long startTime = System.nanoTime();
        if (!inputList.isEmpty()) {
            if (answer == null) {
                startTimeSolve = ZonedDateTime.now();
                answer = solve(inputList);
                long endTime = System.nanoTime();
                solveTime = endTime - startTime;
            }
            logResult(answer);
            String message = String.format("%nSolved at %s%nSolved in %5.5f ms%n",
                    startTimeSolve.format(DateTimeFormatter.ofPattern("HH:mm:ss VV")), solveTime / 1e6);
            LOGGER.info(message + DOTTED_LINE);
        }
    }

    protected List<Integer> commaSeparatedStringToIntegerList(String s) {
        return Arrays.stream(s.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }

    protected String listOfStringListsAsString(List<List<String>> listOfStringLists) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%n"));
        for (List<String> row : listOfStringLists) {
            for (String s : row) {
                sb.append(s).append(", ");
            }
            sb.append(String.format("%n"));
        }
        sb.append(String.format("%n"));
        return sb.toString();
    }

    protected String booleanGrid2DAsString(List<List<Boolean>> grid) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%n"));
        for (List<Boolean> row : grid) {
            for (boolean active : row) {
                sb.append(active ? 1 : 0).append(", ");
            }
            sb.append(String.format("%n"));
        }
        sb.append(String.format("%n"));
        return sb.toString();
    }

    protected String booleanGrid2DAsString(boolean[][] grid) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%n"));
        for (boolean[] row : grid) {
            for (boolean active : row) {
                sb.append(active ? 1 : 0).append(", ");
            }
            sb.append(String.format("%n"));
        }
        sb.append(String.format("%n"));
        return sb.toString();
    }

    protected List<String> loadInputList() {
        return new IOController2().readInputFileByLine(inputFileName);
    }

    protected abstract String solve(List<String> inputList);

    protected String getMessage(String result) {
        return result;
    }

    protected void logResult(String result) {
        LOGGER.info(String.format("%s%nAnswer:%n%s", DOTTED_LINE, getMessage(result)));
    }

    public long getSolveTime() {
        return solveTime;
    }

    public String getPart() {
        return part;
    }

    public Challenge setTitle(String title) {
        this.title = title;
        return this;
    }

    public void clearAnswer() {
        answer = null;
    }

    public String getAnswer() {
        return answer;
    }
}

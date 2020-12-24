package hzt.aoc;

import hzt.aoc.io.IOController2;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static hzt.Launcher.DOTTED_LINE;

public abstract class Challenge {

    protected static final Logger LOGGER = LogManager.getLogger(Challenge.class);

    private String title;
    private final String part;
    private final String description;
    private final String inputFileName;
    private long solveTime = 0;

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
        Object result = solve(inputList);
        long endTime = System.nanoTime();
        solveTime = endTime - startTime;
        logResult(result);
        String message = String.format("Solved in %5.5f ms%n", solveTime / 1e6);
        LOGGER.info(message + DOTTED_LINE);
    }

    protected List<Integer> commaSeparatedStringToIntegerList(String s) {
        return Arrays.stream(s.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }

    protected String listOfStringListsAsString(List<List<String>> listOfStringLists) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%n"));
        for (List<String> row : listOfStringLists) {
            for (String s  : row) {
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

    protected abstract Object solve(List<String> inputList);

    protected void logResult(Object result) {
        LOGGER.info(String.format("%s%nAnswer:%n%s",DOTTED_LINE, result));
    }

    public void logResult() {
        List<String> inputList = loadInputList();
        Object result = solve(inputList);
        LOGGER.info(part + ": " + result);
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
}

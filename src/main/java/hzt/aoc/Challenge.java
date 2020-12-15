package hzt.aoc;

import hzt.aoc.io.IOController2;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

import static hzt.Launcher.DOTTED_LINE;

public abstract class Challenge {

    private static final Logger LOGGER = LogManager.getLogger(Challenge.class);
    private String title;
    private final String note;
    private final String description;
    private final String inputFileName;
    private long solveTime = 0;

    protected Challenge(String part, String description, String inputFileName) {
        this.note = part;
        this.description = description;
        this.inputFileName = inputFileName;
    }

    public void solveChallenge() {
        LOGGER.info(String.format("%n%s %s%nInput: %s%nChallenge: %s%n%s",
                title, note, inputFileName, description, DOTTED_LINE));
        List<String> inputList = loadInputList();
        long startTime = System.nanoTime();
        Object result = solve(inputList);
        long endTime = System.nanoTime();
        solveTime = endTime - startTime;
        logResult(result);
        String message = String.format("Solved in %5.5f ms%n", solveTime / 1e6);
        LOGGER.info(message + DOTTED_LINE);
    }

    protected List<String> loadInputList() {
        return new IOController2().readInputFileByLine(inputFileName);
    }

    protected abstract Object solve(List<String> inputList);

    protected void logResult(Object result) {
        LOGGER.info(String.format("%s%nAnswer:%n%s",DOTTED_LINE, result));
    }

    public long getSolveTime() {
        return solveTime;
    }

    public String getNote() {
        return note;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

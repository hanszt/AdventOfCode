package com.sogeti.codingchallenge;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOController2 implements IIOController {

    private static final Logger LOGGER = LogManager.getLogger(IOController2.class);

    public List<String> readInputFileByLine(String path) {
        List<String> inputList = new ArrayList<>();
        File file = new File(path);
        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                inputList.add(input.nextLine());
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("File with path " + path + " not found...");
        }
        return inputList;
    }
}




package com.sogeti.codingchallenge.io;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOController2 implements IIOController {

    private static final Logger LOGGER = LogManager.getLogger(IOController2.class);

    public List<String> readInputFileByLine(String fileName) {
        URL url = getClass().getResource(RELATIVE_PATH + fileName);
        List<String> inputList = new ArrayList<>();
        if (url != null) {
            try (Scanner input = new Scanner(new File(url.getFile()))) {
                while (input.hasNextLine()) {
                    inputList.add(input.nextLine());
                }
            } catch (FileNotFoundException e) {
                LOGGER.error("File with path " + RELATIVE_PATH + fileName + " not found...");
            }
        } else LOGGER.error("Url is null...");
        return inputList;
    }

    @Override
    public List<String> readInputFileByWord(String fileName) {
        URL url = getClass().getResource(RELATIVE_PATH + fileName);
        List<String> inputList = new ArrayList<>();
        if (url != null) {
            try (Scanner input = new Scanner(new File(url.getFile()))) {
                while (input.hasNext()) {
                    inputList.add(input.next());
                }
            } catch (FileNotFoundException e) {
                LOGGER.error("File with path " + url.getFile() + " not found...");
            }
            LOGGER.info(inputList.size());
        } else LOGGER.error("Url is null...");
        return inputList;
    }
}




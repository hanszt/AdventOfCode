package com.sogeti.codingchallenge;

import java.util.List;

public interface IIOController {

    String NEXT = "nextLine";
    String RELATIVE_PATH = "../../../input/";

    List<String> readInputFileByLine(String path);

    List<String> readInputFileByWord(String path);
}

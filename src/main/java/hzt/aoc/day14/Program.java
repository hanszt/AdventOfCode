package hzt.aoc.day14;

import hzt.aoc.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Program implements Iterable<Pair<String, Integer>> {

    private final String bitMask;
    private final List<Pair<String, Integer>> integerAsBinaryStringsToMemorySpotList = new ArrayList<>();


    public Program(String bitMask) {
        this.bitMask = bitMask;
    }

    public void put(String binaryString, int memSpot) {
        integerAsBinaryStringsToMemorySpotList.add(new Pair<>(binaryString, memSpot));
    }

    @Override
    public Iterator<Pair<String, Integer>> iterator() {
        return integerAsBinaryStringsToMemorySpotList.iterator();
    }

    public Long getValueStoredAfterBitMaskApplication(String binaryString) {
        char[] array = binaryString.toCharArray();
        for (int i = 0; i < binaryString.length(); i++) {
            if (bitMask.charAt(i) != 'X') array[i] = bitMask.charAt(i);
        }
        return Long.parseLong(String.valueOf(array), 2);
    }

    @Override
    public String toString() {
        return "Program{" +
                "bitMask='" + bitMask + '\'' +
                ", integersAsBinaryStringsToMemorySpot=" + integerAsBinaryStringsToMemorySpotList +
                '}';
    }

}

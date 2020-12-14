package hzt.aoc.day14;

import hzt.aoc.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Program implements Iterable<Pair<Integer, Integer>> {

    private static final int BITMASK_LENGTH = 36;

    private final String bitMask;
    private final List<Pair<Integer, Integer>> integerAsBinaryStringsToMemorySpotList = new ArrayList<>();


    public Program(String bitMask) {
        this.bitMask = bitMask;
    }

    public void put(int value, int memSpot) {
        integerAsBinaryStringsToMemorySpotList.add(new Pair<>(value, memSpot));
    }

    @Override
    public Iterator<Pair<Integer, Integer>> iterator() {
        return integerAsBinaryStringsToMemorySpotList.iterator();
    }

    public Long getValueStoredAfterBitMaskApplication(int value) {
        String binaryString36 = convertIntToBinaryString(value);
        char[] array = binaryString36.toCharArray();
        for (int i = 0; i < binaryString36.length(); i++) {
            if (bitMask.charAt(i) != 'X') array[i] = bitMask.charAt(i);
        }
        return Long.parseLong(String.valueOf(array), 2);
    }

    private String convertIntToBinaryString(int integer) {
        String binaryString = Integer.toBinaryString(integer);
        return "0".repeat(BITMASK_LENGTH - binaryString.length()).concat(binaryString);
    }

    public Set<Long> getMemoryLocationsAfterBitMaskApplication(int memoryAddress) {
        String binaryString = convertIntToBinaryString(memoryAddress);
        Set<char[]> possibleMemoryLocations = new HashSet<>();
        char[] array = binaryString.toCharArray();
        possibleMemoryLocations.add(array);
        for (int i = 0; i < binaryString.length(); i++) {
            if (bitMask.charAt(i) == 'X') {
                Set<char[]> copy = new HashSet<>(possibleMemoryLocations);
                for (char[] charArray : possibleMemoryLocations) {
                    char[] newArray = Arrays.copyOf(charArray, charArray.length);
                    charArray[i] = '0';
                    newArray[i] = '1';
                    copy.add(newArray);
                }
                possibleMemoryLocations = new HashSet<>(copy);
            } else if (bitMask.charAt(i) == '1') {
                for (char[] possibleMemoryLocation : possibleMemoryLocations) {
                    possibleMemoryLocation[i] = bitMask.charAt(i);
                }
            }
        }
        return possibleMemoryLocations.stream().map(e -> Long.parseLong(String.valueOf(e), 2)).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "Program{" +
                "bitMask='" + bitMask + '\'' +
                ", integersAsBinaryStringsToMemorySpot=" + integerAsBinaryStringsToMemorySpotList +
                '}';
    }

}

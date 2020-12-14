package hzt.aoc.day14;

import hzt.aoc.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Program2 implements Iterable<Pair<Integer, String>> {

    private final String bitMask;
    private final List<Pair<Integer, String>> integerToMemorySpotAsBinaryStringList = new ArrayList<>();

    public Program2(String bitMask) {
        this.bitMask = bitMask;
    }

    public void put(int value, String memAddress) {
        integerToMemorySpotAsBinaryStringList.add(new Pair<>(value, memAddress));
    }

    @Override
    public Iterator<Pair<Integer, String>> iterator() {
        return integerToMemorySpotAsBinaryStringList.iterator();
    }

    public Set<Long> getMemoryLocationsAfterBitMaskApplication(String binaryString) {
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
        return "Program2{" +
                "bitMask='" + bitMask + '\'' +
                ", integerToMemorySpotAsBinaryStringList=" + integerToMemorySpotAsBinaryStringList +
                '}';
    }
}

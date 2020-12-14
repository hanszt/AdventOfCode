package hzt.aoc.day14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2DockingData extends Day14Challenge {

    public Part2DockingData() {
        super("Docking Data part 2",
                "Execute the initialization program using an emulator for a version 2 decoder chip. What is the sum of all values left in memory after it completes?");
    }

    @Override
    protected Object solve(List<String> inputList) {
        final List<Program2> programs = new ArrayList<>();
        Program2 program = null;
        for (String line : inputList) {
            String[] array = line.split(" = ");
            if (array[0].matches("mask")) {
                program = new Program2(array[1]);
                programs.add(program);
            } else if (program != null) {
                int memoryLocation = Integer.parseInt(array[0].substring(4, array[0].length() - 1));
                String binaryStringMemLocation = Integer.toBinaryString(memoryLocation);
                String binaryStringMemLocation36 = "0".repeat(BITMASK_LENGTH - binaryStringMemLocation.length()).concat(binaryStringMemLocation);
                program.put(Integer.parseInt(array[1]), binaryStringMemLocation36);
            }
        }
        return getMessage(count(programs));
    }

    private long count(List<Program2> programs) {
        final Map<Long, Long> memoryAddressesToValues =  new HashMap<>();
        for (Program2 p : programs) {
            p.forEach(pair -> p.getMemoryLocationsAfterBitMaskApplication(pair.getRight())
                    .forEach(memAdr -> memoryAddressesToValues.put(memAdr, (long) pair.getLeft())));
        }
        return memoryAddressesToValues.values().stream().reduce(Long::sum).orElse(0L);
    }


    @Override
    String getMessage(long value) {
        return String.format("%d", value);
    }
}

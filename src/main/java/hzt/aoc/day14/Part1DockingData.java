package hzt.aoc.day14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part1DockingData extends Day14Challenge {

    public Part1DockingData() {
        super("Docking Data part 1",
                "Execute the initialization program. What is the sum of all values left in memory after it completes?");
    }

    @Override
    protected String solve(List<String> inputList) {
        final List<Program> programs = new ArrayList<>();
        Program program = null;
        for (String line : inputList) {
            String[] array = line.split(" = ");
            if (array[0].matches("mask")) {
                program = new Program(array[1]);
                programs.add(program);
            } else if (program != null) {
                int memoryLocation = Integer.parseInt(array[0].substring(4, array[0].length() - 1));
                String binaryString = Integer.toBinaryString(Integer.parseInt(array[1]));
                String binaryString36 = "0".repeat(BITMASK_LENGTH - binaryString.length()).concat(binaryString);
                program.put(binaryString36, memoryLocation);
            }
        }
        return getMessage(count(programs));
    }

    private long count(List<Program> programs) {
        final Map<Integer, Long> valuesInMemory =  new HashMap<>();
        for (Program p : programs) {
            p.forEach(e -> valuesInMemory.put(e.getRight(), p.getValueStoredAfterBitMaskApplication(e.getLeft())));
        }
        return valuesInMemory.values().stream().reduce(Long::sum).orElse(0L);
    }

    @Override
    String getMessage(long global) {
        return String.format("%d", global);
    }

}

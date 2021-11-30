package hzt.aoc.day14

class Part2DockingData : Day14Challenge(
    "Docking Data part 2",
    "Execute the initialization program using an emulator for a version 2 decoder chip. What is the sum of all values left in memory after it completes"
) {
    override fun count(programs: List<Program>): Long {
        val memoryAddressesToValues: MutableMap<Long, Long> = HashMap()
        for (p in programs) {
            for (pair in p) {
                for (it in p.getMemoryLocationsAfterBitMaskApplication(pair.right)) {
                    memoryAddressesToValues[it] = pair.left.toLong()
                }
            }
        }
        return memoryAddressesToValues.values.stream().reduce { a: Long, b: Long -> java.lang.Long.sum(a, b) }
            .orElse(0L)
    }

    override fun getMessage(value: Long): String {
        return String.format("%d", value)
    }
}

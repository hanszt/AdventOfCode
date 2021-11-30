package hzt.aoc.day14

class Part2DockingData : Day14Challenge(
    "Docking Data part 2",
    "Execute the initialization program using an emulator for a version 2 decoder chip. What is the sum of all values left in memory after it completes"
) {
    override fun count(programs: List<Program>): Long {
        val memoryAddressesToValues: MutableMap<Long, Long> = HashMap()
        for (p in programs) {
            for (pair in p) {
                for (it in p.getMemoryLocationsAfterBitMaskApplication(pair.second)) {
                    memoryAddressesToValues[it] = pair.first.toLong()
                }
            }
        }
        return memoryAddressesToValues.values.sum()
    }

    override fun getMessage(value: Long): String = String.format("%d", value)
}

package hzt.aoc.day14

class Part1DockingData : Day14Challenge(
    "Docking Data part 1",
    "Execute the initialization program. What is the sum of all values left in memory after it completes"
) {
    override fun count(programs: List<Program>): Long {
        val valuesInMemory: MutableMap<Int, Long> = HashMap()
        for (program in programs) {
            for (it in program) {
                valuesInMemory[it.second] = program.getValueStoredAfterBitMaskApplication(it.first)
            }
        }
        return valuesInMemory.values.sum()
    }

    override fun getMessage(value: Long): String = String.format("%d", value)
}

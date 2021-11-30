package hzt.aoc.day14

import hzt.aoc.Pair
import java.util.*
import java.util.stream.Collectors

class Program(private val bitMask: String) : Iterable<Pair<Int, Int>> {
    private val integerAsBinaryStringsToMemorySpotList: MutableList<Pair<Int, Int>> = ArrayList()
    fun put(value: Int, memSpot: Int) {
        integerAsBinaryStringsToMemorySpotList.add(Pair(value, memSpot))
    }

    override fun iterator(): Iterator<Pair<Int, Int>> {
        return integerAsBinaryStringsToMemorySpotList.iterator()
    }

    fun getValueStoredAfterBitMaskApplication(value: Int): Long {
        val binaryString36 = convertIntToBinaryString(value)
        val array = binaryString36.toCharArray()
        for (i in 0 until binaryString36.length) {
            if (bitMask[i] != 'X') array[i] = bitMask[i]
        }
        return String(array).toLong(2)
    }

    fun getMemoryLocationsAfterBitMaskApplication(memoryAddress: Int): Set<Long> {
        val binaryString = convertIntToBinaryString(memoryAddress)
        var possibleMemoryLocations: MutableSet<CharArray> = HashSet()
        val array = binaryString.toCharArray()
        possibleMemoryLocations.add(array)
        for (i in 0 until binaryString.length) {
            if (bitMask[i] == 'X') {
                val copy: MutableSet<CharArray> = HashSet(possibleMemoryLocations)
                for (charArray in possibleMemoryLocations) {
                    val newArray = Arrays.copyOf(charArray, charArray.size)
                    charArray[i] = '0'
                    newArray[i] = '1'
                    copy.add(newArray)
                }
                possibleMemoryLocations = HashSet(copy)
            } else if (bitMask[i] == '1') {
                for (possibleMemoryLocation in possibleMemoryLocations) {
                    possibleMemoryLocation[i] = bitMask[i]
                }
            }
        }
        return possibleMemoryLocations.stream().map { e: CharArray ->
            String(
                e
            ).toLong(2)
        }.collect(Collectors.toSet())
    }

    private fun convertIntToBinaryString(integer: Int): String {
        val binaryString = Integer.toBinaryString(integer)
        return "0".repeat(BITMASK_LENGTH - binaryString.length) + binaryString
    }

    override fun toString(): String {
        return "Program{" +
                "bitMask='" + bitMask + '\'' +
                ", integersAsBinaryStringsToMemorySpot=" + integerAsBinaryStringsToMemorySpotList +
                '}'
    }

    companion object {
        private const val BITMASK_LENGTH = 36
    }
}

package hzt.aoc.day17

import hzt.aoc.Challenge
import java.util.stream.Collectors
import java.util.stream.IntStream

abstract class Day17Challenge internal constructor(challengeTitle: String, description: String) :
    Challenge(challengeTitle, description, "20201217-input-day17.txt") {
    override fun solve(inputList: List<String>): String {
        return solveByGrid(inputList).toString()
    }

    protected abstract fun solveByGrid(inputList: List<String>): Long
    fun getInitGrid3D(inputList: List<String>): MutableList<MutableList<MutableList<Boolean>>> {
        val grid3D: MutableList<MutableList<MutableList<Boolean>>> = ArrayList()
        val grid2D: MutableList<MutableList<Boolean>> = ArrayList()
        for (line in inputList) {
            val charRow = line.toCharArray()
            val row: MutableList<Boolean> = ArrayList()
            for (state in charRow) {
                row.add(state == ACTIVE)
            }
            grid2D.add(row)
        }
        grid3D.add(grid2D)
        return grid3D
    }

    fun copyGrid3D(grid3d: MutableList<MutableList<MutableList<Boolean>>>):
            MutableList<MutableList<MutableList<Boolean>>> {
        val copy: MutableList<MutableList<MutableList<Boolean>>> = ArrayList()
        for (gridXY in grid3d) {
            val newGridXY: MutableList<MutableList<Boolean>> = ArrayList()
            for (rowX in gridXY) {
                val newRowX: MutableList<Boolean> = ArrayList(rowX)
                newGridXY.add(newRowX)
            }
            copy.add(newGridXY)
        }
        return copy
    }

    fun addInactiveOuterLayer3D(grid3d: MutableList<MutableList<MutableList<Boolean>>>) {
        for (gridXY in grid3d) {
            for (rowX in gridXY) {
                rowX.add(0, false)
                rowX.add(false)
            }
            gridXY.add(0, createInActiveRow(gridXY[0].size))
            gridXY.add(createInActiveRow(gridXY[0].size))
        }
        val newWidth = grid3d[0][0].size
        val newHeight = grid3d[0].size
        grid3d.add(0, createInActiveXYPlane(newWidth, newHeight))
        grid3d.add(createInActiveXYPlane(newWidth, newHeight))
    }

    private fun createInActiveRow(width: Int): MutableList<Boolean> {
        return IntStream.range(0, width)
            .mapToObj { false }
            .collect(Collectors.toList())
    }

    private fun createInActiveXYPlane(width: Int, height: Int): MutableList<MutableList<Boolean>> {
        val inActiveGridXY: MutableList<MutableList<Boolean>> = ArrayList()
        for (y in 0 until height) {
            val row = createInActiveRow(width)
            inActiveGridXY.add(row)
        }
        return inActiveGridXY
    }

    fun createInActiveXYZGrid(width: Int, height: Int, depth: Int): MutableList<MutableList<MutableList<Boolean>>> {
        val inActiveGridXYZ: MutableList<MutableList<MutableList<Boolean>>> = ArrayList()
        for (z in 0 until depth) {
            val inActiveGridXY: MutableList<MutableList<Boolean>> = createInActiveXYPlane(width, height)
            inActiveGridXYZ.add(inActiveGridXY)
        }
        return inActiveGridXYZ
    }

    fun grid3DAsString(grid3d: List<List<List<Boolean>>>): String {
        val sb = StringBuilder()
        var z = -(grid3d.size - 1) / 2
        for (gridXY in grid3d) {
            sb.append(String.format("%nSlice at z = %d", z))
            sb.append(booleanGrid2DAsString(gridXY))
            z++
        }
        return sb.toString()
    }

    /*
     If a cube is active and exactly 2 or 3 of its neighbors are also active, the cube remains active. Otherwise, the cube becomes inactive.
     If a cube is inactive but exactly 3 of its neighbors are active, the cube becomes active. Otherwise, the cube remains inactive.
 */
    fun applyRules(active: Boolean, activeNeighbors: Int): Boolean {
        return (active && (activeNeighbors in 2..3)) || (!active && activeNeighbors == 3)
    }

    fun upperBound(curVal: Int, gridDimension: Int): Int {
        return if (curVal + 1 < gridDimension) curVal + 1 else curVal
    }

    override fun getMessage(result: String): String {
        return String.format("%s cubes are left in the active state after %d cycles", result, NUMBER_OF_CYCLES)
    }

    companion object {
        const val NUMBER_OF_CYCLES = 6
        const val ACTIVE = '#'
    }
}

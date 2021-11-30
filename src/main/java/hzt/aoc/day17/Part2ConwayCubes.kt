package hzt.aoc.day17

class Part2ConwayCubes : Part1ConwayCubes(
    "part 2",
    "Starting with your given initial configuration, simulate six cycles in a 4-dimensional space. " +
            "How many cubes are left in the active state after the sixth cycle"
) {
    override fun solveByGrid(inputList: List<String>): Long {
        var grid4d = getInitGrid4D(inputList)
        for (i in 0 until NUMBER_OF_CYCLES) {
            addInactiveOuterLayer4D(grid4d)
            LOGGER.trace("Iteration: $i")
            var w = -(grid4d.size - 1) / 2
            for (grid3D in grid4d) {
                LOGGER.trace(String.format("at w = %d %s", w, grid3DAsString(grid3D)))
                w++
            }
            grid4d = applyRules4D(grid4d)
        }
        return countActive4D(grid4d)
    }

    private fun countActive4D(grid4d: MutableList<MutableList<MutableList<MutableList<Boolean>>>>): Long {
        var active: Long = 0
        for (grid3D in grid4d) {
            active += countActive3D(grid3D)
        }
        return active
    }

    private fun addInactiveOuterLayer4D(grid4d: MutableList<MutableList<MutableList<MutableList<Boolean>>>>) {
        for (grid3d in grid4d) {
            addInactiveOuterLayer3D(grid3d)
        }
        val width = grid4d[0][0][0].size
        val height = grid4d[0][0].size
        val depth = grid4d[0].size
        grid4d.add(0, createInActiveXYZGrid(width, height, depth))
        grid4d.add(createInActiveXYZGrid(width, height, depth))
    }

    private fun getInitGrid4D(inputList: List<String>): MutableList<MutableList<MutableList<MutableList<Boolean>>>> {
        val grid4D: MutableList<MutableList<MutableList<MutableList<Boolean>>>> = ArrayList()
        val grid3D = getInitGrid3D(inputList)
        grid4D.add(grid3D)
        return grid4D
    }

    private fun applyRules4D(grid4d: MutableList<MutableList<MutableList<MutableList<Boolean>>>>): MutableList<MutableList<MutableList<MutableList<Boolean>>>> {
        val newGrid4d = copyGrid4D(grid4d)
        for (w in grid4d.indices) {
            val grid3d = grid4d[w]
            for (z in grid3d.indices) {
                val grid2d = grid3d[z]
                for (y in grid2d.indices) {
                    val row: List<Boolean> = grid2d[y]
                    for (x in row.indices) {
                        var currentActive = row[x]
                        val activeNeighbors = countActiveNeighbors(Point4D(x, y, z, w), grid4d)
                        currentActive = applyRules(currentActive, activeNeighbors)
                        newGrid4d[w][z][y][x] = currentActive
                    }
                }
            }
        }
        addInactiveOuterLayer4D(newGrid4d)
        return newGrid4d
    }

    private fun copyGrid4D(grid4d: MutableList<MutableList<MutableList<MutableList<Boolean>>>>):
            MutableList<MutableList<MutableList<MutableList<Boolean>>>> {
        val copy: MutableList<MutableList<MutableList<MutableList<Boolean>>>> = ArrayList()
        for (grid3d in grid4d) {
            copy.add(copyGrid3D(grid3d))
        }
        return copy
    }

    private fun countActiveNeighbors(cur: Point4D, curGrid4d: List<MutableList<MutableList<MutableList<Boolean>>>>): Int {
        var activeNeighbors = 0
        for (w in (cur.w - 1).coerceAtLeast(0)..upperBound(cur.w, curGrid4d.size)) {
            for (z in (cur.z - 1).coerceAtLeast(0)..upperBound(cur.z, curGrid4d[0].size)) {
                for (y in (cur.y - 1).coerceAtLeast(0)..upperBound(cur.y, curGrid4d[0][0].size)) {
                    for (x in (cur.x - 1).coerceAtLeast(0)..upperBound(cur.x, curGrid4d[0][0][0].size)) {
                        if (isActiveNeighbor(Point4D(x, y, z, w), cur, curGrid4d)) activeNeighbors++
                    }
                }
            }
        }
        return activeNeighbors
    }

    private fun isActiveNeighbor(
        checked: Point4D,
        cur: Point4D,
        curGrid4d: List<MutableList<MutableList<MutableList<Boolean>>>>
    ): Boolean {
        return if (cur != checked) {
            curGrid4d[checked.w][checked.z][checked.y][checked.x]
        } else false
    }
}

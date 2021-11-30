package hzt.aoc.day17

open class Part1ConwayCubes : Day17Challenge {
    constructor() : super(
        "part 1",
        "Starting with your given initial configuration, simulate six cycles. " +
                "How many cubes are left in the active state after the sixth cycle"
    )

    constructor(challengeTitle: String, description: String) : super(challengeTitle, description)

    override fun solveByGrid(inputList: List<String>): Long {
        var grid3d = getInitGrid3D(inputList)
        for (i in 0 until NUMBER_OF_CYCLES) {
            addInactiveOuterLayer3D(grid3d)
            LOGGER.trace(String.format("Iteration: %d%n%s", i, grid3DAsString(grid3d)))
            grid3d = updateGrid(grid3d)
        }
        return countActive3D(grid3d)
    }

    fun countActive3D(grid3d: List<List<MutableList<Boolean>>>): Long {
        return grid3d.stream()
            .flatMap<List<Boolean>> { obj: List<MutableList<Boolean>> -> obj.stream() }
            .flatMap { obj: List<Boolean> -> obj.stream() }
            .filter { b: Boolean -> b }.count()
    }

    private fun updateGrid(grid3d: MutableList<MutableList<MutableList<Boolean>>>):
            MutableList<MutableList<MutableList<Boolean>>> {
        val newGrid3d = copyGrid3D(grid3d)
        for (z in grid3d.indices) {
            val grid2d = grid3d[z]
            for (y in grid2d.indices) {
                val row: List<Boolean> = grid2d[y]
                for (x in row.indices) {
                    var currentActive = row[x]
                    val activeNeighbors = countActiveNeighbors(Point(x, y, z), grid3d)
                    currentActive = applyRules(currentActive, activeNeighbors)
                    newGrid3d[z][y][x] = currentActive
                }
            }
        }
        addInactiveOuterLayer3D(newGrid3d)
        return newGrid3d
    }

    fun countActiveNeighbors(cur: Point, curGrid3d: List<List<MutableList<Boolean>>>): Int {
        var activeNeighbors = 0
        for (z in (cur.z - 1).coerceAtLeast(0)..upperBound(cur.z, curGrid3d.size)) {
            for (y in (cur.y - 1).coerceAtLeast(0)..upperBound(cur.y, curGrid3d[0].size)) {
                for (x in (cur.x - 1).coerceAtLeast(0)..upperBound(cur.x, curGrid3d[0][0].size)) {
                    if (isActiveNeighbor(Point(x, y, z), cur, curGrid3d)) activeNeighbors++
                }
            }
        }
        return activeNeighbors
    }

    private fun isActiveNeighbor(checked: Point, cur: Point, curGrid3d: List<List<MutableList<Boolean>>>): Boolean {
        return cur != checked && curGrid3d[checked.z][checked.y][checked.x]
    }
}

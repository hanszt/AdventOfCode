package hzt.aoc.io

interface IIOController {
    fun readInputFileByLine(path: String): List<String>
    fun readInputFileByWord(path: String): List<String>

    companion object {
        const val RELATIVE_PATH = "../../../input/"
    }
}

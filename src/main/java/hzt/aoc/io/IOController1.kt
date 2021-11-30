package hzt.aoc.io

import org.apache.log4j.LogManager
import java.io.File
import java.io.IOException
import java.nio.file.Files

class IOController1 : IIOController {
    override fun readInputFileByLine(path: String): List<String> {
        val url = javaClass.getResource(IIOController.RELATIVE_PATH + path)
        if (url != null) {
            try {
                return Files.readAllLines(File(url.file).toPath())
            } catch (io: IOException) {
                LOGGER.error("File with path " + IIOController.RELATIVE_PATH + path + " not found...")
                io.printStackTrace()
            }
        } else LOGGER.error("Resource url from relative path " + IIOController.RELATIVE_PATH + path + " is null...")
        return emptyList()
    }

    override fun readInputFileByWord(path: String): List<String> {
        throw UnsupportedOperationException()
    }

    companion object {
        private val LOGGER = LogManager.getLogger(IOController1::class.java)
    }
}

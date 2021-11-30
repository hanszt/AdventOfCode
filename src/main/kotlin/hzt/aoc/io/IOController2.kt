package hzt.aoc.io

import org.apache.log4j.LogManager
import java.io.File
import java.io.FileNotFoundException
import java.util.*

class IOController2 : IIOController {
    override fun readInputFileByLine(path: String): MutableList<String> {
        val url = javaClass.getResource(IIOController.RELATIVE_PATH + path)
        val inputList: MutableList<String> = ArrayList()
        if (url != null) {
            try {
                Scanner(File(url.file)).use { input ->
                    while (input.hasNextLine()) {
                        inputList.add(input.nextLine())
                    }
                }
            } catch (e: FileNotFoundException) {
                LOGGER.error("File with path " + IIOController.RELATIVE_PATH + path + " not found...")
            }
        } else LOGGER.error("Resource url from relative path " + IIOController.RELATIVE_PATH + path + " is null...")
        return inputList
    }

    override fun readInputFileByWord(fileName: String): List<String> {
        val url = javaClass.getResource(IIOController.RELATIVE_PATH + fileName)
        val inputList: MutableList<String> = ArrayList()
        if (url != null) {
            try {
                Scanner(File(url.file)).use { input ->
                    while (input.hasNext()) {
                        inputList.add(input.next())
                    }
                }
            } catch (e: FileNotFoundException) {
                LOGGER.error("File with path " + url.file + " not found...")
            }
            LOGGER.info(inputList.size)
        } else LOGGER.error("Url is null...")
        return inputList
    }

    companion object {
        private val LOGGER = LogManager.getLogger(IOController2::class.java)
    }
}

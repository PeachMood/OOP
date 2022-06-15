package ru.nsu.voronova.file

import java.io.File

class FileManager {
    fun getFilePath(directoryPath: String, file: String): String {
        return "$directoryPath/$file"
    }

    fun createFile(fileName: String, fileContent: String, directoryPath: String = "."): File {
        createDirectory(directoryPath)
        val file = File("$directoryPath/$fileName")
        file.createNewFile()
        file.writeText(fileContent)
        return file
    }

    fun createDirectory(directoryPath: String): File {
        val directory = File(directoryPath)
        directory.mkdir()
        return directory
    }

    fun readFile(fileName: String, directoryPath: String): String {
        return readFile(getFilePath(directoryPath, fileName))
    }

    fun readFile(filePath: String): String {
        val bufferedReader = File(filePath).bufferedReader()
        val fileContent = bufferedReader.readText()
        bufferedReader.close()
        return fileContent
    }
}
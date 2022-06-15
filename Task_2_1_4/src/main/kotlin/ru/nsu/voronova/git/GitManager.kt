package ru.nsu.voronova.git

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.revwalk.RevCommit
import ru.nsu.voronova.file.FileManager
import java.io.File
import java.util.*

class GitManager(val rootDirectoryPath: String = "./git") {
    private val fileManager = FileManager()

    init {
        fileManager.createDirectory(rootDirectoryPath)
    }

    fun cloneRepository(repositoryURL: String, directoryName: String) {
        val directory = fileManager.createDirectory(fileManager.getFilePath(rootDirectoryPath, directoryName))
        if (directory.listFiles()?.isEmpty() == true) {
            Git.cloneRepository()
                .setURI(repositoryURL)
                .setDirectory(directory)
                .call()
        }
    }

    fun checkCommit(directoryName: String, from: Date, to: Date): Boolean {
        val iterator: Iterator<RevCommit> =
            Git.open(File(fileManager.getFilePath(rootDirectoryPath, "$directoryName/.git"))).log().call().iterator()
        while (iterator.hasNext()) {
            val authorDate = iterator.next().authorIdent.getWhen()
            if (from.before(authorDate) && to.after(authorDate)) {
                return true
            }
        }
        return false
    }
}

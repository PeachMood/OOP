package ru.nsu.voronova.html

import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import ru.nsu.voronova.file.FileManager

abstract class ReportBuilder(private val directoryPath: String = ".", private val fileName: String = "report.html") {
    private val fileManager = FileManager()
    val document = createHTMLDocument().html {
        head {
            title("Report")
            style {
                unsafe {
                    raw(
                        "table {" +
                                "width: 100%;\n" +
                                "margin-bottom: 20px;\n" +
                                "border: 1px solid #dddddd;\n" +
                                "border-collapse: collapse; \n" +
                                "text-align: center; \n" +
                                "}\n" +
                                "table th {\n" +
                                "font-weight: bold;\n" +
                                "padding: 5px;\n" +
                                "background: #efefef;\n" +
                                "border: 1px solid #dddddd;\n" +
                                "}\n" +
                                "table td {\n" +
                                "border: 1px solid #dddddd;\n" +
                                "padding: 5px;\n" +
                                "}"
                    )
                }
            }
        }
        body {
            div { id = "main" }
        }
    }

    abstract fun buildHTML()

    fun build() {
        buildHTML()
        fileManager.createDirectory(directoryPath)
        fileManager.createFile(fileName, document.serialize(), directoryPath)
    }
}
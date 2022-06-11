package ru.nsu.voronova.script

import java.io.BufferedReader
import java.io.File
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class ScriptRunner {
    inline fun <reified T> run(script: String, extension: String = "kts"): T {
        val scriptEngine: ScriptEngine = ScriptEngineManager().getEngineByExtension(extension)
        val result: Any = scriptEngine.eval(script)
        return if (result is T) {
            result
        } else {
            throw TypeMismatch(T::class.java.typeName, result.javaClass.typeName)
        }
    }

    inline fun <reified T> run(file: File, extension: String = "kts"): T {
        val bufferedReader: BufferedReader = file.bufferedReader()
        val fileContent: String = bufferedReader.readText()
        bufferedReader.close()
        return run(fileContent, extension)
    }
}

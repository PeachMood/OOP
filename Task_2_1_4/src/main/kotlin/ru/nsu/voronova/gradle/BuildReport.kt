package ru.nsu.voronova.gradle

data class BuildReport(val successful: Boolean, val testReport: String? = null, val documentation: String? = null)

package com.theapache64.readgen

import com.theapache64.readgen.core.ConfigManager

private const val IS_DEBUG = true

class Main

fun main(args: Array<String>) {

    if (ConfigManager.isConfigExist()) {
        val projectDir = if (IS_DEBUG) {
            "/home/theapache64/Documents/projects/readgen"
        } else {
            System.getProperty("user.dir")
        }.toFile()

        when (ProjectExpert.getProjectType(projectDir)) {
            ProjectType.JAR -> {
                val content = ReadMeGenerator.getGenerated(projectDir)
            }
            ProjectType.ANDROID -> {
                println("Coming Soon")
            }
        }
    } else {
        println("""
            ➡️️ No config file found. 
            ➡️️ Creating config file...
            ➡️️ Config file created.
            Edit config.json and try again 
        """.trimIndent())
        ConfigManager.createConfig()
    }
}
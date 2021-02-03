package com.theapache64.readgen

import com.theapache64.readgen.core.ConfigManager
import com.theapache64.readgen.core.ProjectExpert
import com.theapache64.readgen.core.ReadMeCreator
import com.theapache64.readgen.core.ReadMeGenerator
import com.theapache64.readgen.utils.InputUtils
import com.theapache64.readgen.utils.toFile

private const val IS_DEBUG = true

class Main

fun main(args: Array<String>) {

    if (ConfigManager.isConfigExist()) {
        val projectDir = if (IS_DEBUG) {
            "/home/theapache64/Documents/projects/readgen"
        } else {
            System.getProperty("user.dir")
        }.toFile()

        val projectType = ProjectExpert.getProjectType(projectDir)
        val description = InputUtils.getString("Description", true)
        val config = ConfigManager.getConfig()
        val content = ReadMeGenerator.getGenerated(
            config,
            projectDir,
            projectType,
            description
        )
        ReadMeCreator.create(projectDir, content)
        println("Done!")

    } else {
        println(
            """
            ➡️️ No config file found. 
            ➡️️ Creating config file...
            ➡️️ Config file created.
            Edit config.json and try again 
        """.trimIndent()
        )
        ConfigManager.createConfig()
    }
}
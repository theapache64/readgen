package com.theapache64.readgen

import com.theapache64.readgen.core.ConfigManager
import com.theapache64.readgen.core.InstallScriptManager
import com.theapache64.readgen.core.ProjectExpert
import com.theapache64.readgen.core.ReadMeManager
import com.theapache64.readgen.utils.InputUtils
import com.theapache64.readgen.utils.JarUtils
import com.theapache64.readgen.utils.toFile

private const val IS_DEBUG = true
private const val VERSION = "v1.0.0-alpha03"

class Main

@Suppress("UNUSED_PARAMETER")
fun main(args: Array<String>) {
    println("📄 ReadMeGen: $VERSION")
    println("---------------------------")

    if (ConfigManager.isConfigExist()) {
        val projectDir = if (IS_DEBUG) {
            "/home/theapache64/Documents/projects/readgen"
        } else {
            System.getProperty("user.dir")
        }.toFile()

        val projectType = ProjectExpert.getProjectType(projectDir)
        val description = InputUtils.getString("Description", true)
        val config = ConfigManager.getConfig()
        val readMeContent = ReadMeManager.getGenerated(
            config,
            projectDir,
            projectType,
            description
        )
        println("➡️ README content generated")
        ReadMeManager.create(projectDir, readMeContent)
        println("➡️ README.md created")


        // Creating installation script
        println("➡️ Install script generated")
        val script = InstallScriptManager.getGenerated(
            config,
            projectDir
        )

        InstallScriptManager.create(projectDir, script)
        println("➡️ install.sh created")
        println("✔ Done!")

    } else {
        println(
            """
            ➡️️ No config file found. 
            ➡️️ Creating config file...
            ➡️️ Config file created.
            Edit config.json (${JarUtils.getJarDir()}config.json) and try again 
        """.trimIndent()
        )
        ConfigManager.createConfig()
        ConfigManager.openConfig()
    }
}
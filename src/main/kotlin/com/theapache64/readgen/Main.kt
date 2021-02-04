package com.theapache64.readgen

import com.theapache64.readgen.core.*
import com.theapache64.readgen.utils.*
import java.io.File

private const val IS_DEBUG = false
private const val VERSION = "v1.0.0-alpha05"

class Main

@Suppress("UNUSED_PARAMETER")
fun main(args: Array<String>) {
    println("📄 ReadGen: $VERSION")
    println("---------------------------")

    if (ConfigManager.isConfigExist()) {

        val projectDir = if (IS_DEBUG) {
            "/home/theapache64/Documents/projects/readgen"
        } else {
            System.getProperty("user.dir")
        }.toFile()

        val isCoverOnly = args.isNotEmpty() && args.first() == "-c"
        if (isCoverOnly) {
            generateCoverImage(projectDir)
        } else {
            generateReadMe(projectDir)
        }

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

private fun generateReadMe(projectDir: File) {

    if (ReadMeManager.getReadMeFile(projectDir).exists()) {
        error("README.md already exist")
    }

    if (InstallScriptManager.getInstallationScript(projectDir).exists()) {
        error("'install.sh' already exist")
    }

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

    // Creating cover image
    generateCoverImage(projectDir)

    println("✔ Done!")
}

private fun generateCoverImage(projectDir: File) {
    println("➡️ Generating cover image... please wait")
    CoverGenerator.generate(projectDir.name)
    println(
        "➡️ Cover image generated. " +
                "If you didn't like the cover, try ${ANSI_GREEN}readgen -c${ANSI_RESET} to generate a new one (-c = cover only)"
    )
}
package com.theapache64.readgen

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import com.theapache64.readgen.core.*
import com.theapache64.readgen.model.ProjectType
import com.theapache64.readgen.utils.*

class ReadGenCommand : CliktCommand() {

    companion object {
        private const val IS_DEBUG = false
        private const val VERSION = "v1.0.3"
        private const val DEFAULT_FONT_SIZE = 130
    }

    private val projectDir by lazy {
        if (IS_DEBUG) {
            "/home/theapache64/Documents/projects/readgen"
        } else {
            System.getProperty("user.dir")
        }.toFile()
    }

    private val customFont: String? by option(
        help = "Custom font",
        names = arrayOf("-f")
    )

    private val fontSize: Int by option(
        help = "Font size",
        names = arrayOf("-s")
    ).int().default(DEFAULT_FONT_SIZE)

    private val isCoverOnly by option(
        help = "Cover only",
        names = arrayOf("-c")
    ).flag()

    override fun run() {

        println("📄 ReadGen: $VERSION")
        println("---------------------------")

        if (ConfigManager.isConfigExist()) {


            if (isCoverOnly) {
                generateCoverImage()
            } else {
                generateReadMe()
            }

        } else {
            println(
                """
            ➡️️ No config file found. 
            ➡️️ Creating config file...
            ➡️️ Config file created.
            
            Edit config.json (${ConfigManager.configFile.absolutePath}) and try again 
        """.trimIndent()
            )
            ConfigManager.createConfig()
            ConfigManager.openConfig()
        }
    }


    private fun generateReadMe() {

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


        if (projectType == ProjectType.JAR) {
            // Creating installation script
            println("➡️ Install script generated")
            val script = InstallScriptManager.getGenerated(
                config,
                projectDir
            )


            InstallScriptManager.create(projectDir, script)
            println("➡️ install.sh created")
        }

        // Creating cover image
        generateCoverImage()

        println("✔ Done!")
    }

    private fun generateCoverImage() {
        println("➡️ Generating cover image... please wait")
        CoverGenerator.generate(projectDir.name, fontSize, customFont)
        println(
            "➡️ Cover image generated. " +
                    "If you didn't like the cover, try ${ANSI_GREEN}readgen -c$ANSI_RESET to generate a new one (-c = cover only)"
        )
    }

}
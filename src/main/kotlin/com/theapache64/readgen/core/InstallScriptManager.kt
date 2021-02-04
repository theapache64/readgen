package com.theapache64.readgen.core

import com.theapache64.readgen.model.Config
import com.theapache64.readgen.utils.getResource
import java.io.File

object InstallScriptManager {

    fun getGenerated(config: Config, projectDir: File): String {
        return getResource("install_format.sh")
            .replace(ReadMeManager.KEY_GITHUB_USERNAME, config.githubUsername)
            .replace(ReadMeManager.KEY_PROJECT_NAME, projectDir.name)
    }

    fun create(projectDir: File, script: String) {
        val scriptFile = projectDir.resolve("install.sh")
        if (scriptFile.exists()) {
            error("'install.sh' already exist")
        }
        scriptFile.writeText(script)
    }

}
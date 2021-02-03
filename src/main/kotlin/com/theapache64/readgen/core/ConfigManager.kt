package com.theapache64.readgen.core

import com.theapache64.readgen.model.Config
import com.theapache64.readgen.utils.JarUtils
import com.theapache64.readgen.utils.JsonUtils
import kotlinx.serialization.encodeToString
import java.io.File

object ConfigManager {

    private val configFile by lazy {
        File("${JarUtils.getJarDir()}config.json")
    }

    fun isConfigExist(): Boolean {
        return configFile.exists()
    }

    fun createConfig() {
        configFile.writeText(
            getDummyConfig()
        )
    }

    private fun getDummyConfig(): String {
        val config = Config(
            "ENTER_YOUR_GITHUB_USERNAME_HERE"
        )
        return JsonUtils.json.encodeToString(config)
    }

}
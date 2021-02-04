package com.theapache64.readgen.core

import com.theapache64.readgen.model.Config
import com.theapache64.readgen.utils.JarUtils
import com.theapache64.readgen.utils.JsonUtils
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import java.awt.Desktop
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
            authorName = "ENTER_YOUR_NAME",
            githubUsername = "ENTER_YOUR_GITHUB_USERNAME_HERE",
            twitterUsername = "ENTER_YOUR_TWITTER_USERNAME_HERE",
            email = "ENTER_YOUR_EMAIL_HERE",
            patronUsername = "ENTER_YOUR_PATRON_USERNAME",
            buyMeCoffeeUsername = "ENTER_BMC_USERNAME",
            paypalUsername = "ENTER_YOUR_PAYPAL_USERNAME"
        )
        return JsonUtils.json.encodeToString(config)
    }

    fun getConfig(): Config {
        return JsonUtils.json.decodeFromString(configFile.readText())
    }

    fun openConfig() {
        Desktop.getDesktop().open(configFile)
    }

}
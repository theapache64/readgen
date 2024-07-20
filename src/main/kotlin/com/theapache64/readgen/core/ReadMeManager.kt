package com.theapache64.readgen.core

import com.theapache64.readgen.model.Config
import com.theapache64.readgen.model.ProjectType
import com.theapache64.readgen.utils.getResource
import java.io.File
import java.util.*

object ReadMeManager {

    private const val TEMPLATE_PROJECT_CONTAINER = "PROJECT_CONTAINER.md"
    private const val TEMPLATE_JAR_PROJECT = "JAR_PROJECT.md"
    private const val TEMPLATE_ANDROID_PROJECT = "ANDROID_PROJECT.md"
    private const val TEMPLATE_GENERIC_PROJECT = "GENERIC_PROJECT.md"

    private const val KEY_AUTHOR_NAME = "{{authorName}}"
    const val KEY_PROJECT_NAME = "{{projectName}}"
    private const val KEY_PROJECT_DESC = "{{projectDescription}}"
    private const val KEY_TYPE_CONTENT = "{{typeContent}}"
    const val KEY_GITHUB_USERNAME = "{{githubUsername}}"
    private const val KEY_TWITTER_USERNAME = "{{twitterUsername}}"
    private const val KEY_PATRON_USERNAME = "{{patronUsername}}"
    private const val KEY_EMAIL = "{{email}}"
    private const val KEY_BUYMECOFFEE_USERNAME = "{{buyMeCoffeeUsername}}"
    private const val KEY_PAYPAL_USERNAME = "{{paypalUsername}}"
    private const val KEY_CURRENT_YEAR = "{{currentYear}}"
    private const val KEY_CONSTRUCTION_GIF = "{{constructionGif}}"

    fun getGenerated(
        config: Config,
        projectName: String,
        projectType: ProjectType,
        description: String
    ): String {

        val typeContent = when (projectType) {
            ProjectType.JAR -> {
                getResource(TEMPLATE_JAR_PROJECT)
            }
            ProjectType.ANDROID -> {
                getResource(TEMPLATE_ANDROID_PROJECT)
            }
            ProjectType.GENERIC -> {
                getResource(TEMPLATE_GENERIC_PROJECT)
            }
        }

        return getResource(TEMPLATE_PROJECT_CONTAINER)
            .replace(KEY_TYPE_CONTENT, typeContent)
            .replace(KEY_PROJECT_NAME, projectName)
            .replace(KEY_CONSTRUCTION_GIF, GifManager.constructionGifs.random())
            .replace(KEY_AUTHOR_NAME, config.authorName)
            .replace(KEY_PROJECT_DESC, description)
            .replace(KEY_GITHUB_USERNAME, config.githubUsername)
            .replace(KEY_TWITTER_USERNAME, config.twitterUsername)
            .replace(KEY_PATRON_USERNAME, config.patronUsername)
            .replace(KEY_EMAIL, config.email)
            .replace(KEY_BUYMECOFFEE_USERNAME, config.buyMeCoffeeUsername)
            .replace(KEY_PAYPAL_USERNAME, config.paypalUsername)
            .replace(KEY_CURRENT_YEAR, Calendar.getInstance().get(Calendar.YEAR).toString())
    }

    fun create(projectDir: File, content: String, isOverrideReadme: Boolean) {
        val readMeFile = getReadMeFile(projectDir)
        require(isOverrideReadme || readMeFile.exists().not()) { "README.md already exist" }
        readMeFile.writeText(content)
    }

    fun getReadMeFile(projectDir: File): File {
        return projectDir.resolve("README.md")
    }

}
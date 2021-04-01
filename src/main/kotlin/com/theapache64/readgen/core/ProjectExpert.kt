package com.theapache64.readgen.core

import com.theapache64.readgen.model.ProjectType
import java.io.File


/**
 * A class to identify project related things.
 */
object ProjectExpert {

    private const val PLUGIN_ANDROID = "com.android.tools.build:gradle"
    private const val PLUGIN_KOTLIN = "org.jetbrains.kotlin.jvm"

    /**
     * To get project type from given project root
     */
    fun getProjectType(projectRoot: File): ProjectType {
        val gradleFile = getGradleFile(projectRoot)

        if (gradleFile.exists().not()) {
            // project is not gradle based. considering it as a normal project.
            return ProjectType.GENERIC
        }

        val gradleContent = gradleFile.readText()
        return when {
            gradleContent.contains(PLUGIN_ANDROID) -> {
                ProjectType.ANDROID
            }
            gradleContent.contains(PLUGIN_KOTLIN) -> {
                ProjectType.JAR
            }
            else -> {
                ProjectType.GENERIC
            }
        }
    }

    private fun getGradleFile(projectRoot: File): File {
        var gradleFile = projectRoot.resolve("build.gradle")
        if (gradleFile.exists().not()) {
            gradleFile = projectRoot.resolve("build.gradle.kts")
        }
        return gradleFile
    }
}
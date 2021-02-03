package com.theapache64.readgen

import java.io.File


/**
 * A class to identify project related things.
 */
object ProjectExpert {

    private const val PLUGIN_ANDROID = "com.android.tools.build:gradle"
    private const val PLUGIN_KOTLIN = "org.jetbrains.kotlin.jvm"
    private const val DIR_CORE = "core"

    /**
     * To get project type from given project root
     */
    @Throws(IllegalStateException::class)
    fun getProjectType(projectRoot: File): ProjectType {
        val gradleFile = getGradleFile(projectRoot)
        checkIfGradleProject(gradleFile)

        val gradleContent = gradleFile.readText()
        return when {
            gradleContent.contains(PLUGIN_ANDROID) -> {
                ProjectType.ANDROID
            }
            gradleContent.contains(PLUGIN_KOTLIN) -> {
                ProjectType.JAR
            }
            else -> {
                error("Invalid project type")
            }
        }


    }

    private fun checkIfGradleProject(gradleFile: File) {
        if (!gradleFile.exists()) {
            error("${gradleFile.parentFile.name} is not a gradle project")
        }
    }

    private fun getGradleFile(projectRoot: File) = projectRoot.resolve("build.gradle")

    /**
     * To get jar type
     */
    @Throws(IllegalStateException::class)
    fun getJarType(projectRoot: File): JarType {
        checkIfGradleProject(getGradleFile(projectRoot))
        val coreDir = projectRoot.resolve(DIR_CORE)
        return if (coreDir.exists()) {
            JarType.FAMILY_JAR
        } else {
            JarType.SINGLE_JAR
        }
    }
}
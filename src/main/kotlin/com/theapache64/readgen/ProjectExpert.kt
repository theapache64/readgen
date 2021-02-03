package com.theapache64.readgen

import java.io.File


object ProjectExpert {

    private const val PLUGIN_ANDROID = "com.android.tools.build:gradle"
    private const val PLUGIN_KOTLIN = "org.jetbrains.kotlin.jvm"

    /**
     * To get project type from given project root
     */
    @Throws(IllegalStateException::class)
    fun getProjectType(projectRoot: File): ProjectType {
        val gradleFile = projectRoot.resolve("build.gradle")
        if (gradleFile.exists()) {
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
        } else {
            error("${projectRoot.name} is not a gradle project")
        }
    }
}
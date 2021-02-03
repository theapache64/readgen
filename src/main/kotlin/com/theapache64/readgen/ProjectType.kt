package com.theapache64.readgen

enum class ProjectType {
    JAR, ANDROID
}

enum class JarType {
    /**
     * A jar without any dependency
     */
    SINGLE_JAR,

    /**
     * A jar with dependant files or folders
     */
    FAMILY_JAR
}
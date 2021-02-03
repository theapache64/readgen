package com.theapache64.readgen.core

import java.io.File

object ReadMeCreator {
    fun create(projectDir: File, content: String) {
        val readMeFile = projectDir.resolve("README.md")
        require(readMeFile.exists().not()) { "README.md already exist" }
        readMeFile.writeText(content)
    }
}
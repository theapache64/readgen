package com.theapache64.readgen.utils

import com.theapache64.readgen.ReadGenCommand
import java.io.File

object JarUtils {

    fun getJarDir(): String {

        val jarDir = File(
            ReadGenCommand::class.java.protectionDomain.codeSource.location
                .toURI()
        ).parent

        if (jarDir.contains("/out/production") || jarDir.contains("/build/classes/")) {
            return ""
        }

        return "$jarDir/"
    }
}

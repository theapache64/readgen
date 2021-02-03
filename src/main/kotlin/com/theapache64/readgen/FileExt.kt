package com.theapache64.readgen

import java.io.File

fun String.toFile(): File {
    return File(this)
}

fun getResource(fileName: String): String {
    return Main::class.java.getResource("/$fileName").readText()
}

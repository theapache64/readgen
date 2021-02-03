package com.theapache64.readgen.utils

import kotlinx.serialization.json.Json

object JsonUtils {
    val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }
}
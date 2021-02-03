package com.theapache64.readgen.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Config(
    @SerialName("github_username")
    val githubUsername: String, // theapache64
)
package com.theapache64.readgen.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Config(
    @SerialName("github_username")
    val githubUsername: String, // theapache64
    @SerialName("twitter_username")
    val twitterUsername: String, // theapache64
    @SerialName("email")
    val email: String, // theapache64
)
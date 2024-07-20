package com.theapache64.readgen.model
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName

@Serializable
data class UnsplashApiResponse(
    @SerialName("urls")
    val urls: Urls
)

@Serializable
data class Urls(
    @SerialName("raw")
    val raw: String // https://images.unsplash.com/photo-1485827404703-89b55fcc595e?ixid=M3w2Mjc5OTV8MHwxfHJhbmRvbXx8fHx8fHx8fDE3MTk2NTU1MzF8&ixlib=rb-4.0.3
)
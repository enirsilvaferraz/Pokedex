package com.example.pokedex.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class NamedApiResource(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)
package com.example.pokedex.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FlavorText(
    @SerialName("flavor_text")
    val flavorText: String,
    @SerialName("language")
    val language: NamedApiResource,
    @SerialName("version")
    val version: NamedApiResource // 'version' in the JSON also has 'name' and 'url'
)
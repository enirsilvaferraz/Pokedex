package com.example.pokedex.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokedexDescription(
    @SerialName("description")
    val description: String,
    @SerialName("language")
    val language: NamedApiResource
)
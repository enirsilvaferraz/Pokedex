package com.example.pokedex.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokedexName(
    @SerialName("name")
    val name: String,
    @SerialName("language")
    val language: NamedApiResource
)
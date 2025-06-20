package com.example.pokedex.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokedexName(
    @SerialName("name")
    val name: String,
    @SerialName("language")
    val language: NamedApiResource
)
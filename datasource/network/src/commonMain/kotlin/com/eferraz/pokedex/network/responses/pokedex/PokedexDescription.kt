package com.eferraz.pokedex.network.responses.pokedex

import com.eferraz.pokedex.network.responses.NamedApiResource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PokedexDescription(
    @SerialName("description")
    val description: String,
    @SerialName("language")
    val language: NamedApiResource
)
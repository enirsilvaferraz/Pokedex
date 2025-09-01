package com.eferraz.pokedex.network.responses.pokedex

import com.eferraz.pokedex.network.responses.NamedApiResource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PokedexName(
    @SerialName("name")
    val name: String,
    @SerialName("language")
    val language: NamedApiResource
)
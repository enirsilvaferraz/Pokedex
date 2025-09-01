package com.eferraz.pokedex.network.responses.species

import com.eferraz.pokedex.network.responses.NamedApiResource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Genus(
    @SerialName("genus")
    val genus: String,
    @SerialName("language")
    val language: NamedApiResource
)
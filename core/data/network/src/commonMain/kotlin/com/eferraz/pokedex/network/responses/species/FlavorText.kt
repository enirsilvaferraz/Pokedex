package com.eferraz.pokedex.network.responses.species

import com.eferraz.pokedex.network.responses.NamedApiResource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class FlavorText(
    @SerialName("flavor_text")
    val flavorText: String,
    @SerialName("language")
    val language: NamedApiResource,
    @SerialName("version")
    val version: NamedApiResource // 'version' in the JSON also has 'name' and 'url'
)
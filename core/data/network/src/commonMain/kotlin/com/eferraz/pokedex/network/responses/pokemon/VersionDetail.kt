package com.eferraz.pokedex.network.responses.pokemon

import com.eferraz.pokedex.network.responses.NamedApiResource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class VersionDetail(
    @SerialName("rarity")
    val rarity: Int,
    @SerialName("version")
    val version: NamedApiResource
)
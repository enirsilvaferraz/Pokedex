package com.example.pokedex.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class VersionDetail(
    @SerialName("rarity")
    val rarity: Int,
    @SerialName("version")
    val version: NamedApiResource
)

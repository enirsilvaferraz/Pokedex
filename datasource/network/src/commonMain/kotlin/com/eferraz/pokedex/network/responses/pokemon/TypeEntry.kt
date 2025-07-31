package com.eferraz.pokedex.network.responses.pokemon

import com.eferraz.pokedex.network.responses.NamedApiResource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TypeEntry(
    @SerialName("slot")
    val slot: Int,
    @SerialName("type")
    val type: NamedApiResource
)
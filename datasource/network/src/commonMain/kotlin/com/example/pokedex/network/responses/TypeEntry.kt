package com.example.pokedex.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TypeEntry(
    @SerialName("slot")
    val slot: Int,
    @SerialName("type")
    val type: NamedApiResource
)

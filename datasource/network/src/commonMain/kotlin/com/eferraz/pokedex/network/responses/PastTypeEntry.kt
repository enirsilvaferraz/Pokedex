package com.eferraz.pokedex.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PastTypeEntry(
    @SerialName("generation")
    val generation: NamedApiResource,
    @SerialName("types")
    val types: List<TypeEntry>
)

package com.eferraz.pokedex.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class StatEntry(
    @SerialName("base_stat")
    val baseStat: Int,
    @SerialName("effort")
    val effort: Int,
    @SerialName("stat")
    val stat: NamedApiResource
)

package com.eferraz.pokedex.network.responses.pokemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Cries(
    @SerialName("latest")
    val latest: String?,
    @SerialName("legacy")
    val legacy: String?
)
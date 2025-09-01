package com.eferraz.pokedex.network.responses.pokemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class OfficialArtwork(
    @SerialName("front_default")
    val frontDefault: String?,
    @SerialName("front_shiny")
    val frontShiny: String?
)
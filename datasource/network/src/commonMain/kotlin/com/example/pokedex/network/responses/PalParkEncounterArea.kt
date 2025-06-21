package com.example.pokedex.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PalParkEncounterArea(
    @SerialName("area")
    val area: NamedApiResource,
    @SerialName("base_score")
    val baseScore: Int,
    @SerialName("rate")
    val rate: Int
)
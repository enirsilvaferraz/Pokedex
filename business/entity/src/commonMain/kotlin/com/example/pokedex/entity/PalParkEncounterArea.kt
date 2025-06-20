package com.example.pokedex.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PalParkEncounterArea(
    @SerialName("area")
    val area: NamedApiResource,
    @SerialName("base_score")
    val baseScore: Int,
    @SerialName("rate")
    val rate: Int
)
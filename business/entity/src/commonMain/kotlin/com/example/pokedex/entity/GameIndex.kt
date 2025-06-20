package com.example.pokedex.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameIndex(
    @SerialName("game_index")
    val gameIndex: Int,
    @SerialName("version")
    val version: NamedApiResource
)

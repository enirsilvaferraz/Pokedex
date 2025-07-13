package com.eferraz.pokedex.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GameIndex(
    @SerialName("game_index")
    val gameIndex: Int,
    @SerialName("version")
    val version: NamedApiResource
)

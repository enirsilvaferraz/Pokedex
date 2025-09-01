package com.eferraz.pokedex.network.responses.pokemon

import com.eferraz.pokedex.network.responses.NamedApiResource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Ability(
    @SerialName("ability")
    val ability: NamedApiResource?,
    @SerialName("is_hidden")
    val isHidden: Boolean,
    @SerialName("slot")
    val slot: Int
)
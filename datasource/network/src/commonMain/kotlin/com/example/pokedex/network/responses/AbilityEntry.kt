package com.example.pokedex.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AbilityEntry(
    @SerialName("ability")
    val ability: NamedApiResource,
    @SerialName("is_hidden")
    val isHidden: Boolean,
    @SerialName("slot")
    val slot: Int
)

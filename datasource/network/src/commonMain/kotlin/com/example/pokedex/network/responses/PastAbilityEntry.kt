package com.example.pokedex.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PastAbilityEntry(
    @SerialName("abilities")
    val abilities: List<Ability>,
    @SerialName("generation")
    val generation: NamedApiResource
)

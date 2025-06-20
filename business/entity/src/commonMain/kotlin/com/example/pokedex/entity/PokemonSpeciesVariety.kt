package com.example.pokedex.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpeciesVariety(
    @SerialName("is_default")
    val isDefault: Boolean,
    @SerialName("pokemon")
    val pokemon: NamedApiResource
)
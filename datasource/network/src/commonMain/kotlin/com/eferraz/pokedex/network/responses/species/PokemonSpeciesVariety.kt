package com.eferraz.pokedex.network.responses.species

import com.eferraz.pokedex.network.responses.NamedApiResource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PokemonSpeciesVariety(
    @SerialName("is_default")
    val isDefault: Boolean,
    @SerialName("pokemon")
    val pokemon: NamedApiResource
)
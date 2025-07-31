package com.eferraz.pokedex.network.responses.pokemon

import com.eferraz.pokedex.network.responses.pokemon.Ability
import com.eferraz.pokedex.network.responses.NamedApiResource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PastAbilityEntry(
    @SerialName("abilities")
    val abilities: List<Ability>,
    @SerialName("generation")
    val generation: NamedApiResource
)
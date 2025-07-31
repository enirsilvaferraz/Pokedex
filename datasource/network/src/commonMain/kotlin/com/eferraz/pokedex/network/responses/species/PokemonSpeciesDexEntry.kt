package com.eferraz.pokedex.network.responses.species

import com.eferraz.pokedex.network.responses.NamedApiResource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PokemonSpeciesDexEntry(
    @SerialName("entry_number")
    val entryNumber: Int,
    @SerialName("pokedex")
    val pokedex: NamedApiResource
)
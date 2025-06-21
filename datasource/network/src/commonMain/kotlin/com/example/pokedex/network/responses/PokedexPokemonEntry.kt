package com.example.pokedex.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PokedexPokemonEntry(
    @SerialName("entry_number")
    val entryNumber: Long,
    @SerialName("pokemon_species")
    val pokemonSpecies: NamedApiResource
)
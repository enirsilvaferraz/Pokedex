package com.example.pokedex.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpeciesDexEntry(
    @SerialName("entry_number")
    val entryNumber: Int,
    @SerialName("pokedex")
    val pokedex: NamedApiResource
)
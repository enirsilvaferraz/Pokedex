package com.example.pokedex.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokedex(
    @SerialName("id")
    val id: Long,
    @SerialName("is_main_series")
    val isMainSeries: Boolean,
    @SerialName("name")
    val name: String,
    @SerialName("descriptions")
    val descriptions: List<PokedexDescription>,
    @SerialName("names")
    val names: List<PokedexName>,
    @SerialName("pokemon_entries")
    val pokemonEntries: List<PokedexPokemonEntry>
)

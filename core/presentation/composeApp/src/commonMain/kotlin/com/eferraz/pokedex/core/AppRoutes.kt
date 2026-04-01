package com.eferraz.pokedex.core

import kotlinx.serialization.Serializable

@Serializable
internal object PokemonListRouting

@Serializable
internal data class PokemonDetailRouting(
    val id: Long,
    val name: String,
    val artwork: String,
    val type1: String?,
)

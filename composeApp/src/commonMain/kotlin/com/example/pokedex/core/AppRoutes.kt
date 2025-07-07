package com.example.pokedex.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

internal object AppRoutes {

    @Serializable
    object PokemonListScreen

    @Serializable
    data class PokemonDetailScreen(@SerialName("id") val id: Long)
}
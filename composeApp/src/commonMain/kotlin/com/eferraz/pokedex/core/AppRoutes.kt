package com.eferraz.pokedex.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal object PokemonListRouting

@Serializable
internal data class PokemonDetailRouting(@SerialName("id") val id: Long)
package com.example.pokedex.network.adapters

import com.example.pokedex.entity.PokemonVO
import kotlinx.coroutines.flow.Flow

interface PokedexAdapter {
    fun get(): Flow<PokemonVO>
}
package com.example.repositories

import com.example.pokedex.entity.PokemonVO
import kotlinx.coroutines.flow.Flow

interface PokedexRepository {
    fun get(): Flow<PokemonVO>
}
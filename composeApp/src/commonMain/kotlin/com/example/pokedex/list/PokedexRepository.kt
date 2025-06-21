package com.example.pokedex.list

import com.example.pokedex.entity.PokemonVO
import kotlinx.coroutines.flow.Flow

internal interface PokedexRepository {
    fun get(): Flow<PokemonVO>
}
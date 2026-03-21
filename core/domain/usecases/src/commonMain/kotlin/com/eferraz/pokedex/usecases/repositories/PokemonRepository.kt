package com.eferraz.pokedex.usecases.repositories

import com.eferraz.pokedex.entity.BasePokemon
import kotlinx.coroutines.flow.Flow

public interface PokemonRepository {
    public fun getAll(): Flow<List<BasePokemon>>
    public suspend fun fetch(id: Long)
}
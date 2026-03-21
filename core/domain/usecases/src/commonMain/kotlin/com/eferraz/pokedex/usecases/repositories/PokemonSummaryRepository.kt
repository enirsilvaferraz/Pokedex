package com.eferraz.pokedex.usecases.repositories

import com.eferraz.pokedex.entity.summary.PokemonSummary
import kotlinx.coroutines.flow.Flow

public interface PokemonSummaryRepository {
    public fun getAll(): Flow<List<PokemonSummary>>
}
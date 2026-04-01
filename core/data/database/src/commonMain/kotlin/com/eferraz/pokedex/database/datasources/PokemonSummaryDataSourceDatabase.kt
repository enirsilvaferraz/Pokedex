package com.eferraz.pokedex.database.datasources

import com.eferraz.pokedex.entity.summary.PokemonSummary
import kotlinx.coroutines.flow.Flow

public interface PokemonSummaryDataSourceDatabase {
    public fun get(limit: Int, offset: Int): Flow<List<PokemonSummary>>
    public suspend fun upsert(vararg pokemons: PokemonSummary)
}

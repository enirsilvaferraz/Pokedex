package com.eferraz.pokedex.network.datasources

import com.eferraz.pokedex.entity.summary.PokemonSummary

public interface PokemonSummaryDataSourceApi {
    public suspend fun get(limit: Int, offset: Int): List<PokemonSummary>
    public suspend fun get(id: Long): PokemonSummary
}
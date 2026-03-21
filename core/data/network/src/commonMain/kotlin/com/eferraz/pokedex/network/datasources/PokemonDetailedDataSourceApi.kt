package com.eferraz.pokedex.network.datasources

import com.eferraz.pokedex.entity.detail.PokemonDetailed

public interface PokemonDetailedDataSourceApi {
    public suspend fun get(id: Long): PokemonDetailed
}
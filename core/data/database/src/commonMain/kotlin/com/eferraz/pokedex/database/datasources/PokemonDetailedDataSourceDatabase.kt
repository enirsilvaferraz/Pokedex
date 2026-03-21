package com.eferraz.pokedex.database.datasources

import com.eferraz.pokedex.entity.detail.PokemonDetailed

public interface PokemonDetailedDataSourceDatabase {
    public suspend fun upsert(entity: PokemonDetailed)
    public suspend fun get(id: Long): PokemonDetailed?
}
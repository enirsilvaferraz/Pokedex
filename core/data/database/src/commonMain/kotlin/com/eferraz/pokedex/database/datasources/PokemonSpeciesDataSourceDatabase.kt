package com.eferraz.pokedex.database.datasources

import com.eferraz.pokedex.entity.detail.Species

public interface PokemonSpeciesDataSourceDatabase {
    public suspend fun upsert(entity: Species)
}

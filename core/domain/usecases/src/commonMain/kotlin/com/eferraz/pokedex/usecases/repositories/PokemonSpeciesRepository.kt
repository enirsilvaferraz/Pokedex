package com.eferraz.pokedex.usecases.repositories

import com.eferraz.pokedex.entity.detail.Species

public interface PokemonSpeciesRepository {
    public suspend fun get(id: Long): Species
    public suspend fun upsert(entity: Species)
}

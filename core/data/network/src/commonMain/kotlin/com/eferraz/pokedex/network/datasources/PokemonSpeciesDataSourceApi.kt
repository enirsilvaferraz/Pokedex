package com.eferraz.pokedex.network.datasources

import com.eferraz.pokedex.entity.detail.Species

public interface PokemonSpeciesDataSourceApi {
    public suspend fun get(id: Long): Species
}
package com.eferraz.pokedex.network.datasources

import com.eferraz.pokedex.entity.PokemonComplete

public interface PokemonDataSourceNetwork {
    public suspend fun get(id: Int): PokemonComplete
}
package com.eferraz.pokedex.network.datasources

import com.eferraz.pokedex.entity.PokemonCompleteVO

public interface PokemonDataSourceNetwork {
    public suspend fun get(id: Int): PokemonCompleteVO
}
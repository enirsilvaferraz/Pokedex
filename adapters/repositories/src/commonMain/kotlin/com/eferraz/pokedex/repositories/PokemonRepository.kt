package com.eferraz.pokedex.repositories

import com.eferraz.pokedex.entity.PokemonCompleteVO

public interface PokemonRepository {
    public suspend fun get(id: Long): PokemonCompleteVO
}
package com.eferraz.pokedex.usecases.repositories

import com.eferraz.pokedex.entity.PokemonCompleteVO

public interface PokemonRepository {
    public suspend fun get(id: Long): PokemonCompleteVO
}
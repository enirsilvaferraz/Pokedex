package com.eferraz.pokedex.usecases.repositories

import com.eferraz.pokedex.entity.PokemonComplete

public interface PokemonRepository {
    public suspend fun get(id: Long): PokemonComplete
}
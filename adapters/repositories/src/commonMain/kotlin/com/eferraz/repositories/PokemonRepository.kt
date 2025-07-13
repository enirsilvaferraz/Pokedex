package com.eferraz.repositories

import com.eferraz.pokedex.entity.PokemonVO

public interface PokemonRepository {
    public suspend fun get(id: Long): PokemonVO
}
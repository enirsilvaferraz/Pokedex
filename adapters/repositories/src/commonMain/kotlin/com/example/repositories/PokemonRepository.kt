package com.example.repositories

import com.example.pokedex.entity.PokemonVO

public interface PokemonRepository {
    public suspend fun get(id: Long): PokemonVO
}
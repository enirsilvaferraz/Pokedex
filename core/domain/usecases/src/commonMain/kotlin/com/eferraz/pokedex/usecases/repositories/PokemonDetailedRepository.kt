package com.eferraz.pokedex.usecases.repositories

public interface PokemonDetailedRepository {
    public suspend fun fetch(id: Long)
}
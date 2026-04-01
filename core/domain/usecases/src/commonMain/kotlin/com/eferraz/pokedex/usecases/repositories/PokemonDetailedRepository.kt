package com.eferraz.pokedex.usecases.repositories

import com.eferraz.pokedex.entity.detail.PokemonDetailed

public interface PokemonDetailedRepository {
    public suspend fun get(id: Long): PokemonDetailed?
    public suspend fun fetch(id: Long): PokemonDetailed
    public suspend fun upsert(entity: PokemonDetailed)
}

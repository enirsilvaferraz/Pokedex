package com.eferraz.pokedex.database.datasources

import com.eferraz.pokedex.entity.PokemonComplete
import com.eferraz.pokedex.entity.PokemonLight

public interface PokemonDataSourceDatabase {
    public suspend fun insert(entity: PokemonComplete)
    public suspend fun getLight(id: Int): PokemonLight?
    public suspend fun getComplete(id: Int): PokemonComplete?
}
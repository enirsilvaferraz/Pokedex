package com.eferraz.pokedex.database.datasources

import com.eferraz.pokedex.entity.PokemonCompleteVO
import com.eferraz.pokedex.entity.PokemonLightVO

public interface PokemonDataSourceDatabase {
    public suspend fun insert(entity: PokemonCompleteVO)
    public suspend fun getLight(id: Int): PokemonLightVO?
    public suspend fun getComplete(id: Int): PokemonCompleteVO?
}
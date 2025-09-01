package com.eferraz.pokedex.repositories.datasources

import com.eferraz.pokedex.entity.PokemonCompleteVO
import com.eferraz.pokedex.entity.PokemonLightVO

public interface PokemonDataSource {

    public interface Network {
        public suspend fun get(id: Int): PokemonCompleteVO
    }

    public interface Database {
        public suspend fun insert(entity: PokemonCompleteVO)
        public suspend fun getLight(id: Int): PokemonLightVO?
        public suspend fun getComplete(id: Int): PokemonCompleteVO?
    }
}
package com.eferraz.repositories.datasources

import com.eferraz.pokedex.entity.PokemonVO

public interface PokemonDataSource {

    public interface Network {
        public suspend fun get(id: Int): PokemonVO
    }

    public interface Database {
        public suspend fun insert(entity: PokemonVO)
        public suspend fun get(id: Int): PokemonVO?
    }
}
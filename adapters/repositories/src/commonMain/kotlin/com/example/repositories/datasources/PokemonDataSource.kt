package com.example.repositories.datasources

import com.example.pokedex.entity.PokemonVO

public interface PokemonDataSource {

    public interface Network {
        public suspend fun get(id: Int): PokemonVO
    }

    public interface Database {
        public suspend fun insert(entity: PokemonVO)
        public suspend fun get(id: Int): PokemonVO?
    }
}
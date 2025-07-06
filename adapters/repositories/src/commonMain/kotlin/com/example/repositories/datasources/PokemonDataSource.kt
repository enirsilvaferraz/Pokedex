package com.example.repositories.datasources

import com.example.pokedex.entity.PokemonVO

public interface PokemonDataSource {

    public interface Network {
        public suspend fun get(limit: Int, offset: Int): List<PokemonVO>
    }

    public interface Database {
        public suspend fun insert(entities: List<PokemonVO>)
        public suspend fun get(limit: Int, offset: Int): List<PokemonVO>
    }
}
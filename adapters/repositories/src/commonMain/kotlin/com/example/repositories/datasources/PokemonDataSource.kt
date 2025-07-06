package com.example.repositories.datasources

import com.example.pokedex.entity.PokemonVO
import kotlinx.coroutines.flow.Flow

interface PokemonDataSource {

    interface Network {
        suspend fun get(limit: Int, offset: Int): List<PokemonVO>
    }

    interface Database {
        suspend fun insert(entities: List<PokemonVO>)
        suspend fun get(limit: Int, offset: Int): List<PokemonVO>
    }
}
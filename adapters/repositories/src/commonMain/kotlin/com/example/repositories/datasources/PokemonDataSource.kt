package com.example.repositories.datasources

import com.example.pokedex.entity.PokemonVO
import kotlinx.coroutines.flow.Flow

interface PokemonDataSource {

    interface Network {
        suspend fun get(id: Long): Flow<PokemonVO>
    }

    interface Database {
        suspend fun getAll(): Flow<List<PokemonVO>>
        suspend fun insert(entities: List<PokemonVO>)
        suspend fun update(vararg entities: PokemonVO)
    }
}
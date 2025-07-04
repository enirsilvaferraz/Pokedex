package com.example.repositories

import com.example.pokedex.entity.PokemonVO
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getAll(): Flow<List<PokemonVO>>
    suspend fun populateDatabase()
}
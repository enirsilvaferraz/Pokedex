package com.example.pokedex.network.datasources

import com.example.pokedex.network.responses.Pokemon

interface PokemonDataSource {
    suspend fun get(id: Long): Pokemon
}
package com.example.pokedex.network.datasources

import com.example.pokedex.network.responses.Pokemon

internal interface PokemonDataSource {
    suspend fun get(id: Long): Pokemon
}
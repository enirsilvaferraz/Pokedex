package com.example.pokedex.network.datasources

import com.example.pokedex.network.responses.Pokemon

internal interface PokemonApi {
    suspend fun get(id: Long): Pokemon
}
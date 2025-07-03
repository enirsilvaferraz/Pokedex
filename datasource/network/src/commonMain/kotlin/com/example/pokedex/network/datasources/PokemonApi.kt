package com.example.pokedex.network.datasources

import com.example.pokedex.network.responses.NamedApiResource
import com.example.pokedex.network.responses.Pokemon

internal interface PokemonApi {
    suspend fun get(id: Long): Pokemon

    suspend fun get(limit: Int, offset: Int): List<NamedApiResource>
}
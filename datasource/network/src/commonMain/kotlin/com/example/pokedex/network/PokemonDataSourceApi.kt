package com.example.pokedex.network

import com.example.pokedex.entity.Pokemon
import com.example.pokedex.network.configurations.ClientConfig
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

class PokemonDataSourceApi(
    private val config: ClientConfig
) : PokemonDataSource {

    override suspend fun get(id: Long): Pokemon = config.client.get {
        url("pokemon/$id")
    }.body<Pokemon>()
}
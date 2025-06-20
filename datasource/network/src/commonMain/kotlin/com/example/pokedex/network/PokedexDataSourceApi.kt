package com.example.pokedex.network

import com.example.pokedex.entity.Pokedex
import com.example.pokedex.network.configurations.ClientConfig
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

class PokedexDataSourceApi(
    private val config: ClientConfig
) : PokedexDataSource {

    override suspend fun getAll(): Pokedex = config.client.get {
        url("pokedex/2")
    }.body<Pokedex>()
}


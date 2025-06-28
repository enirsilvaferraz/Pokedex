package com.example.pokedex.network.datasources

import com.example.pokedex.network.responses.Pokedex
import com.example.pokedex.network.core.ClientConfig
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

internal class PokedexApiImpl(
    private val config: ClientConfig
) : PokedexApi {

    override suspend fun get(): Pokedex = config.client.get {
        url("pokedex/2")
    }.body<Pokedex>()
}


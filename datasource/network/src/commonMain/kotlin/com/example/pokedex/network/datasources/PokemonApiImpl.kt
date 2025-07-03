package com.example.pokedex.network.datasources

import com.example.pokedex.network.core.ClientConfig
import com.example.pokedex.network.responses.NamedApiResource
import com.example.pokedex.network.responses.Pokemon
import com.example.pokedex.network.responses.ServiceOffsetResult
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

internal class PokemonApiImpl(
    private val config: ClientConfig,
) : PokemonApi {

    override suspend fun get(id: Long) = config.client.get {
        url("pokemon/$id")
    }.body<Pokemon>()

    override suspend fun get(limit: Int, offset: Int) = config.client.get {
        url("pokemon")
        parameter("limit", limit)
        parameter("offset", offset)
    }.body<ServiceOffsetResult<NamedApiResource>>().results

}
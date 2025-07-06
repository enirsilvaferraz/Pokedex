package com.example.pokedex.network.datasources

import com.example.pokedex.network.helpers.toVO
import com.example.pokedex.network.core.ClientConfig
import com.example.pokedex.network.helpers.emitFlow
import com.example.pokedex.network.responses.Pokemon
import com.example.repositories.datasources.PokemonDataSource
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.coroutines.flow.lastOrNull

internal class PokemonApi(
    private val config: ClientConfig,
) : PokemonDataSource.Network {

    private fun get(id: Long) = emitFlow {
        config.client.get {
            url("pokemon/$id")
        }.body<Pokemon>().toVO()
    }

    override suspend fun get(limit: Int, offset: Int) = (offset + 1).let { relativeOffset ->
        (relativeOffset until (relativeOffset + limit)).mapNotNull { id ->
            get(id.toLong()).lastOrNull()
        }
    }
}
package com.example.pokedex.network.datasources

import com.example.pokedex.entity.PokemonVO
import com.example.pokedex.network.core.ClientConfig
import com.example.pokedex.network.helpers.toVO
import com.example.pokedex.network.responses.Pokemon
import com.example.pokedex.network.responses.PokemonSpeciesDetail
import com.example.repositories.datasources.PokemonDataSource
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

internal class PokemonApi(
    private val config: ClientConfig,
) : PokemonDataSource.Network {

    override suspend fun get(id: Int): PokemonVO = coroutineScope {

        val pokemon = async { pokemon(id) }
        val species = async { species(id) }

        toVO(pokemon.await(), species.await())
    }

    private suspend fun pokemon(id: Int) = config.client.get {
        url("pokemon/$id")
    }.body<Pokemon>()

    private suspend fun species(id: Int) = config.client.get {
        url("pokemon-species/$id")
    }.body<PokemonSpeciesDetail>()
}
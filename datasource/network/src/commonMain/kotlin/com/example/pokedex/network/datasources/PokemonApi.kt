package com.example.pokedex.network.datasources

import com.example.pokedex.network.core.ClientConfig
import com.example.pokedex.network.helpers.toVO
import com.example.pokedex.network.responses.Pokemon
import com.example.repositories.datasources.PokemonDataSource
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

internal class PokemonApi(
    private val config: ClientConfig,
) : PokemonDataSource.Network {

    override suspend fun get(id: Int) = config.client.get {
        url("pokemon/$id")
    }.body<Pokemon>().toVO()
}

//internal class PokemonSpeciesApi(
//    private val config: ClientConfig,
//) : PokemonDataSource.Network {
//
//    private suspend fun get(id: Long) =
//        config.client.get {
//            url("pokemon-species/$id")
//        }.body<PokemonSpeciesDetail>()
//}

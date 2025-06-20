package com.example.pokedex.network

import com.example.pokedex.entity.PokemonSpeciesDetail
import com.example.pokedex.network.configurations.ClientConfig
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

class PokemonSpeciesDataSourceApi(
    private val config: ClientConfig
) : PokemonSpeciesDataSource {

    override suspend fun get(id: Long): PokemonSpeciesDetail = config.client.get {
        url("pokemon-species/$id")
    }.body<PokemonSpeciesDetail>()
}
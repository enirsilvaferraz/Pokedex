package com.eferraz.pokedex.network

import com.eferraz.pokedex.network.core.ClientConfig
import com.eferraz.pokedex.network.responses.NamedApiResource
import com.eferraz.pokedex.network.responses.ServiceOffsetResult
import com.eferraz.pokedex.network.responses.pokemon.PokemonResponse
import com.eferraz.pokedex.network.responses.species.PokemonSpeciesDetail
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import org.koin.core.annotation.Factory

@Factory
internal class PokeApi(
    private val config: ClientConfig,
) {

    suspend fun pokemon(limit: Int, offset: Int) = config.client.get {
        url("pokemon")
        parameter("limit", limit)
        parameter("offset", offset)
    }.body<ServiceOffsetResult<NamedApiResource>>()

    suspend fun pokemon(id: Long) = config.client.get {
        url("pokemon/$id")
    }.body<PokemonResponse>()

    suspend fun species(id: Long) = config.client.get {
        url("pokemon-species/$id")
    }.body<PokemonSpeciesDetail>()
}

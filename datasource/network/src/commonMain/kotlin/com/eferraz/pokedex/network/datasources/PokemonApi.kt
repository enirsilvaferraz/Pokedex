package com.eferraz.pokedex.network.datasources

import com.eferraz.pokedex.entity.PokemonCompleteVO
import com.eferraz.pokedex.entity.PokemonVO
import com.eferraz.pokedex.network.core.ClientConfig
import com.eferraz.pokedex.network.relationship.PokemonAndSpecie
import com.eferraz.pokedex.network.responses.Pokemon
import com.eferraz.pokedex.network.responses.PokemonSpeciesDetail
import com.eferraz.pokedex.repositories.datasources.PokemonDataSource
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.koin.core.annotation.Factory

@Factory
internal class PokemonApi(
    private val config: ClientConfig,
) : PokemonDataSource.Network {

    override suspend fun get(id: Int): PokemonCompleteVO = coroutineScope {

        val pokemon = async { pokemon(id) }
        val species = async { species(id) }

        PokemonAndSpecie(
            pokemon = pokemon.await(),
            species = species.await()
        ).toModel()
    }

    private suspend fun pokemon(id: Int) = config.client.get {
        url("pokemon/$id")
    }.body<Pokemon>()

    private suspend fun species(id: Int) = config.client.get {
        url("pokemon-species/$id")
    }.body<PokemonSpeciesDetail>()
}
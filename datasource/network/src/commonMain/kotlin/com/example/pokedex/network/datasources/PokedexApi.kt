package com.example.pokedex.network.datasources

import com.example.pokedex.entity.PokedexVO
import com.example.pokedex.entity.PokemonVO
import com.example.pokedex.network.core.ClientConfig
import com.example.pokedex.network.responses.Pokedex
import com.example.repositories.datasources.PokedexDataSource
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class PokedexApi(
    private val config: ClientConfig,
) : PokedexDataSource.Network {

    override suspend fun get(id: Int) = emitFlow {
        config.client.get { url("pokedex/$id") }.body<Pokedex>().toVO()
    }


    private fun Pokedex.toVO() = PokedexVO(
        id = id,
        name = name,
        pokemon = pokemonEntries.map {
            PokemonVO(it.entryNumber, it.pokemonSpecies.name)
        }
    )
}

internal fun <T : Any> emitFlow(body: suspend () -> T) = flow {
    emit(body())
}.flowOn(Dispatchers.IO)
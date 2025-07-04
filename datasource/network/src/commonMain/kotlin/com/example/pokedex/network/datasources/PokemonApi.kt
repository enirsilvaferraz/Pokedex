package com.example.pokedex.network.datasources

import com.example.pokedex.entity.PokemonVO
import com.example.pokedex.entity.TypeVO
import com.example.pokedex.network.core.ClientConfig
import com.example.pokedex.network.responses.Pokemon
import com.example.pokedex.network.responses.TypeEntry
import com.example.repositories.datasources.PokemonDataSource
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

internal class PokemonApi(
    private val config: ClientConfig,
) : PokemonDataSource.Network {

    override suspend fun get(id: Long) = emitFlow {
        config.client.get {
            url("pokemon/$id")
        }.body<Pokemon>().toVO()
    }

//    override suspend fun get(limit: Int, offset: Int) = config.client.get {
//        url("pokemon")
//        parameter("limit", limit)
//        parameter("offset", offset)
//    }.body<ServiceOffsetResult<NamedApiResource>>().results

    private fun Pokemon.toVO(): PokemonVO = PokemonVO(
        id = id,
        name = name,
        image = sprites.frontDefault,
        type1 = types.find { it.slot == 1 }?.transform(),
        type2 = types.find { it.slot == 2 }?.transform(),
    )

    private fun TypeEntry.transform(): TypeVO = TypeVO(
        id = type.url.removeSuffix("/").let { it.substring(it.lastIndexOf("/") + 1) }.toLong(),
        name = type.name,
    )
}
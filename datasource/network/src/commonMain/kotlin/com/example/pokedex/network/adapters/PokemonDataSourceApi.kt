package com.example.pokedex.network.adapters

import com.example.pokedex.entity.PokemonVO
import com.example.pokedex.entity.TypeVO
import com.example.pokedex.network.datasources.PokemonApi
import com.example.pokedex.network.responses.Pokemon
import com.example.pokedex.network.responses.TypeEntry
import com.example.repositories.datasources.ReadableDataSource

internal class PokemonDataSourceApi(
    private val pokemonDS: PokemonApi,
) : ReadableDataSource<PokemonVO> {

    override suspend fun get(limit: Int, offset: Int) = pokemonDS.get(limit, offset).map { pokemonDS.get(it.getId()).transform() }

    private fun Pokemon.transform(): PokemonVO = PokemonVO(
        id = id,
        name = name,
        image = sprites.frontDefault,
        type1 = types.find { it.slot == 1 }?.transform()!!,
        type2 = types.find { it.slot == 2 }?.transform()
    )

    private fun TypeEntry.transform(): TypeVO = TypeVO(
        id = type.url.removeSuffix("/").let { it.substring(it.lastIndexOf("/") + 1) }.toLong(),
        name = type.name,
    )
}
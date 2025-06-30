package com.example.pokedex.network.adapters

import com.example.pokedex.entity.PokemonVO
import com.example.pokedex.entity.TypeVO
import com.example.pokedex.network.datasources.PokedexApi
import com.example.pokedex.network.datasources.PokemonApi
import com.example.pokedex.network.responses.PokedexPokemonEntry
import com.example.pokedex.network.responses.Pokemon
import com.example.pokedex.network.responses.TypeEntry
import com.example.repositories.datasources.ReadableDataSource

internal class PokemonDataSourceApi(
    private val podekexDS: PokedexApi,
    private val pokemonDS: PokemonApi,
) : ReadableDataSource<PokemonVO> {

    override suspend fun getAll() =
        podekexDS.get().pokemonEntries.map { it.transform() }.map { it.transform() }

    private suspend fun PokedexPokemonEntry.transform(): Pokemon = pokemonDS.get(entryNumber)

    private fun Pokemon.transform(): PokemonVO = PokemonVO(
        id = id,
        name = name,
        types = types.map { it.transform() },
        image = sprites.frontDefault
    )

    private fun TypeEntry.transform(): TypeVO = TypeVO(
        id = type.url.removeSuffix("/").let { it.substring(it.lastIndexOf("/") + 1) }.toLong(),
        name = type.name,
    )
}
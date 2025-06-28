package com.example.pokedex.network.adapters

import com.example.pokedex.entity.PokemonVO
import com.example.pokedex.network.datasources.PokedexApi
import com.example.pokedex.network.datasources.PokemonApi
import com.example.pokedex.network.responses.Pokemon
import com.example.repositories.datasources.ReadableDataSource
import kotlinx.coroutines.flow.flow

internal class PokemonDataSourceApi(
    private val podekexDS: PokedexApi,
    private val pokemonDS: PokemonApi
) : ReadableDataSource<PokemonVO> {

    override suspend fun getAll() = flow {
        emit(
            podekexDS.get().pokemonEntries.map { entry ->
                pokemonDS.get(entry.entryNumber)
            }.map(::toVO)
        )
    }

    fun toVO(pokemon: Pokemon) = PokemonVO(
        id = pokemon.id,
        name = pokemon.name,
        types = pokemon.types.map { it.type.name.orEmpty() },
        image = pokemon.sprites.frontDefault
    )
}
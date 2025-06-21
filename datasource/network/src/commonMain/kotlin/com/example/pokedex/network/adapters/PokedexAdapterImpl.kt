package com.example.pokedex.network.adapters

import com.example.pokedex.entity.PokemonVO
import com.example.pokedex.network.datasources.PokedexDataSourceApi
import com.example.pokedex.network.datasources.PokemonDataSourceApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class PokedexAdapterImpl(
    private val podekexDS: PokedexDataSourceApi,
    private val pokemonDS: PokemonDataSourceApi
) : PokedexAdapter {

    override fun get(): Flow<PokemonVO> = flow {

        podekexDS.get().pokemonEntries.forEach { entry ->

            pokemonDS.get(entry.entryNumber).let { pokemon ->

                emit(
                    PokemonVO(
                        id = pokemon.id,
                        name = pokemon.name,
                        types = pokemon.types.map { it.type.name.orEmpty() },
                        url = pokemon.sprites.frontDefault
                    )
                )
            }
        }
    }
}
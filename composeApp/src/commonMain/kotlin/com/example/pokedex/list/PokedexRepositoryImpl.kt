package com.example.pokedex.list

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.example.pokedex.PokemonVO
import com.example.pokedex.network.PokedexDataSourceApi
import com.example.pokedex.network.PokemonDataSourceApi
import com.example.pokedex.ui.theme.getColorForType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class PokedexRepositoryImpl(
    private val podekexDS: PokedexDataSourceApi,
    private val pokemonDS: PokemonDataSourceApi
) : PokedexRepository {

    override fun get(): Flow<PokemonVO> = flow {

        val pokedex = podekexDS.get()

        pokedex.pokemonEntries.forEach { entry ->

            val pokemon = pokemonDS.get(entry.entryNumber)

            emit(
                PokemonVO(
                    id = pokemon.id,
                    name = pokemon.name.capitalize(Locale.Companion.current),
                    types = pokemon.types.map { it.type.name.orEmpty() },
                    url = pokemon.sprites.frontDefault
                )
            )
        }
    }
}
package com.eferraz.pokedex.network.datasources

import com.eferraz.pokedex.network.PokeApi
import org.koin.core.annotation.Factory

@Factory
internal class PokemonDetailedDataSourceApiImpl(
    private val pokeApi: PokeApi,
) : PokemonDetailedDataSourceApi {

    override suspend fun get(id: Long) =
        pokeApi.pokemon(id).toModel()
}

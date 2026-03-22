package com.eferraz.pokedex.network.datasources

import com.eferraz.pokedex.network.PokeApi
import org.koin.core.annotation.Factory

@Factory
internal class PokemonSpeciesDataSourceApiImpl(
    private val pokeApi: PokeApi,
) : PokemonSpeciesDataSourceApi {

    override suspend fun get(id: Long) =
        pokeApi.species(id).toModel()
}
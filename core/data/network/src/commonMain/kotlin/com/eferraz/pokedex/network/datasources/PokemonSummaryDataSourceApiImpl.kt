package com.eferraz.pokedex.network.datasources

import com.eferraz.pokedex.entity.summary.PokemonSummary
import com.eferraz.pokedex.network.PokeApi
import com.eferraz.pokedex.network.mappers.toModel
import org.koin.core.annotation.Factory

@Factory
internal class PokemonSummaryDataSourceApiImpl(
    private val pokeApi: PokeApi,
) : PokemonSummaryDataSourceApi {

    override suspend fun get(limit: Int, offset: Int): List<PokemonSummary> =
        pokeApi.pokemon(limit, offset).results.map(::toModel)

    override suspend fun get(id: Long): PokemonSummary =
        toModel(pokeApi.pokemon(id))
}

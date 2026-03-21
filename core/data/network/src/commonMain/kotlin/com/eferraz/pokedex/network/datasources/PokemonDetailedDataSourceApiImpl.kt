package com.eferraz.pokedex.network.datasources

import com.eferraz.pokedex.entity.detail.PokemonDetailed
import com.eferraz.pokedex.network.PokeApi
import com.eferraz.pokedex.network.relationship.PokemonAndSpecie
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.koin.core.annotation.Factory

@Factory
internal class PokemonDetailedDataSourceApiImpl(
    private val pokeApi: PokeApi,
) : PokemonDetailedDataSourceApi {

    override suspend fun get(id: Long): PokemonDetailed = coroutineScope {

        val pokemon = async { pokeApi.pokemon(id) } // todo nao precisa bater aqui de novo
        val species = async { pokeApi.speciesById(id) }

        PokemonAndSpecie(
            pokemon = pokemon.await(),
            species = species.await(),
        ).toModel()
    }
}

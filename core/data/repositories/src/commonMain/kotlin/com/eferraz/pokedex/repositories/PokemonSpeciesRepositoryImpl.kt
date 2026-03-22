package com.eferraz.pokedex.repositories

import com.eferraz.pokedex.database.datasources.PokemonSpeciesDataSourceDatabase
import com.eferraz.pokedex.entity.detail.Species
import com.eferraz.pokedex.network.datasources.PokemonSpeciesDataSourceApi
import com.eferraz.pokedex.usecases.repositories.PokemonSpeciesRepository
import org.koin.core.annotation.Factory

@Factory
internal class PokemonSpeciesRepositoryImpl(
    private val api: PokemonSpeciesDataSourceApi,
    private val database: PokemonSpeciesDataSourceDatabase,
) : PokemonSpeciesRepository {

    override suspend fun get(id: Long) =
        api.get(id = id)

    override suspend fun upsert(entity: Species) {
        database.upsert(entity)
    }
}

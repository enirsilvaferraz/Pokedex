package com.eferraz.pokedex.repositories

import com.eferraz.pokedex.database.datasources.PokemonDetailedDataSourceDatabase
import com.eferraz.pokedex.entity.detail.PokemonDetailed
import com.eferraz.pokedex.network.datasources.PokemonDetailedDataSourceApi
import com.eferraz.pokedex.usecases.repositories.PokemonDetailedRepository
import org.koin.core.annotation.Factory

@Factory
internal class PokemonDetailedRepositoryImpl(
    private val api: PokemonDetailedDataSourceApi,
    private val database: PokemonDetailedDataSourceDatabase,
) : PokemonDetailedRepository {

    override suspend fun get(id: Long) =
        database.get(id = id) ?: fetch(id = id)

    override suspend fun fetch(id: Long) =
        api.get(id = id)
            .also { database.upsert(it) }

    override suspend fun upsert(entity: PokemonDetailed) {
        database.upsert(entity)
    }
}
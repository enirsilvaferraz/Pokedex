package com.eferraz.pokedex.repositories

import com.eferraz.pokedex.database.datasources.PokemonSummaryDataSourceDatabase
import com.eferraz.pokedex.entity.BasePokemon
import com.eferraz.pokedex.network.datasources.PokemonSummaryDataSourceApi
import com.eferraz.pokedex.usecases.repositories.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import org.koin.core.annotation.Factory

@Factory
public class PokemonRepositoryImpl(
    private val api: PokemonSummaryDataSourceApi,
    private val database: PokemonSummaryDataSourceDatabase,
) : PokemonRepository {

    override fun getAll(): Flow<List<BasePokemon>> =
        database.get(limit = KANTO_LIMIT, offset = DEFAULT_OFFSET)
            .onEach { if (it.isEmpty()) fetch() }

    private suspend fun fetch() {
        api.get(limit = KANTO_LIMIT, offset = DEFAULT_OFFSET)
            .also { database.upsert(*it.toTypedArray()) }
    }

    override suspend fun fetch(id: Long) {
        database.upsert(api.get(id))
    }

    private companion object {
        const val KANTO_LIMIT: Int = 151
        const val DEFAULT_OFFSET: Int = 0
    }
}
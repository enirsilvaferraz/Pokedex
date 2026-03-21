package com.eferraz.pokedex.repositories

import com.eferraz.pokedex.database.datasources.PokemonSummaryDataSourceDatabase
import com.eferraz.pokedex.network.datasources.PokemonSummaryDataSourceApi
import com.eferraz.pokedex.usecases.repositories.PokemonSummaryRepository
import kotlinx.coroutines.flow.onEach
import org.koin.core.annotation.Factory

@Factory
internal class PokemonSummaryRepositoryImpl(
    private val api: PokemonSummaryDataSourceApi,
    private val database: PokemonSummaryDataSourceDatabase,
) : PokemonSummaryRepository {

    override fun getAll() =
        database.get(limit = KANTO_LIMIT, offset = DEFAULT_OFFSET)
            .onEach { if (it.isEmpty()) fetch() }

    private suspend fun fetch() {
        api.get(limit = KANTO_LIMIT, offset = DEFAULT_OFFSET)
            .also { database.upsert(*it.toTypedArray()) }
    }

    private companion object {
        const val KANTO_LIMIT: Int = 151
        const val DEFAULT_OFFSET: Int = 0
    }
}
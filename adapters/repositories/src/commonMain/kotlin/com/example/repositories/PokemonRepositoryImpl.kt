package com.example.repositories

import com.example.pokedex.entity.PokemonVO
import com.example.repositories.datasources.ReadableDataSource
import com.example.repositories.datasources.WritableDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

internal class PokemonRepositoryImpl(
    private val readableDataSource: List<ReadableDataSource<PokemonVO>>,
    private val writableDataSource: List<WritableDataSource<PokemonVO>>,
) : PokemonRepository {

    override suspend fun getAll() = withContext(Dispatchers.IO) {
        readableDataSource.firstNotNullOfOrNull {
            it.getAll().takeIf { flow -> flow.first().isNotEmpty() }?.also {
                writableDataSource.forEach { dataSource -> dataSource.insert(it.first()) }
            }
        }
    }
}
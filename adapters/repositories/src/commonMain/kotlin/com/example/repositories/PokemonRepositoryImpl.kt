package com.example.repositories

import com.example.pokedex.entity.PokemonVO
import com.example.repositories.datasources.ReadableDataSource
import com.example.repositories.datasources.WritableDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class PokemonRepositoryImpl(
    private val readableDataSource: List<ReadableDataSource<PokemonVO>>,
    private val writableDataSource: List<WritableDataSource<PokemonVO>>,
) : PokemonRepository {

    override suspend fun getAll() = flow {

        readableDataSource.firstNotNullOfOrNull { dataSource ->

            dataSource.get(152,0).takeIf { it.isNotEmpty() }?.let { models ->

                models.forEach {
                    emit(it)
                }

                writableDataSource.forEach { dataSource -> dataSource.insert(models) }
            }
        }
    }.flowOn(Dispatchers.IO)
}
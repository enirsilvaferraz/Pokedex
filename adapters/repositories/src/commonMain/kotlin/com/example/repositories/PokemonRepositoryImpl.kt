package com.example.repositories

import com.example.pokedex.entity.PokemonVO
import com.example.repositories.datasources.ReadableDataSource
import kotlinx.coroutines.flow.first

internal class PokemonRepositoryImpl(
    private val readableDataSource: List<ReadableDataSource<PokemonVO>>,
) : PokemonRepository {

    override suspend fun getAll() = readableDataSource.firstNotNullOfOrNull {
        it.getAll().takeIf { flow -> flow.first().isNotEmpty() }
    }
}
package com.example.repositories

import com.example.repositories.datasources.PokemonDataSource

internal class PokemonRepositoryImpl(
    private val dataSource: PokemonDataSource.Database,
) : PokemonRepository {

    override suspend fun get(id: Long) = dataSource.get(id.toInt()) ?: throw Exception("Pokemon not found")
}
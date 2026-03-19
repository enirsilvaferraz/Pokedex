package com.eferraz.pokedex.repositories

import com.eferraz.pokedex.database.datasources.PokemonDataSourceDatabase
import com.eferraz.pokedex.usecases.repositories.PokemonRepository
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Provided

@Factory(binds = [PokemonRepository::class])
internal class PokemonRepositoryImpl(
    @Provided private val dataSource: PokemonDataSourceDatabase,
) : PokemonRepository {

    override suspend fun get(id: Long) = dataSource.getComplete(id.toInt()) ?: throw Exception("Pokemon not found")
}
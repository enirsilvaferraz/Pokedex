package com.example.pokedex.database.datasources

import com.example.pokedex.database.daos.PokemonDao
import com.example.pokedex.database.daos.TypeDao
import com.example.pokedex.database.helpers.toTable
import com.example.pokedex.database.helpers.toVO
import com.example.pokedex.entity.PokemonVO
import com.example.repositories.datasources.PokemonDataSource

internal class PokemonDataSourceDB(
    private val pokemonDao: PokemonDao,
    private val typeDao: TypeDao,
) : PokemonDataSource.Database {

    override suspend fun get(limit: Int, offset: Int): List<PokemonVO> =
        pokemonDao.get(limit = limit, offset = offset).map { it.toVO() }

    override suspend fun insert(entities: List<PokemonVO>) {

        (entities.mapNotNull { it.type1 } + entities.mapNotNull { it.type2 })
            .map { it.toTable() }
            .distinctBy { it.id }
            .also {
                typeDao.insert(*it.toTypedArray())
            }

        entities
            .map { it.toTable() }
            .also { pokemon ->
                pokemonDao.insert(*pokemon.toTypedArray())
            }
    }
}
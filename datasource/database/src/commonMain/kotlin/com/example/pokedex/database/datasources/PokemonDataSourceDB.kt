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

    override suspend fun get(id: Int): PokemonVO? =
        pokemonDao.get(id)?.toVO()

    override suspend fun insert(entity: PokemonVO) {

        listOf(entity.type1, entity.type2)
            .mapNotNull { it?.toTable() }
            .distinctBy { it.id }
            .also {
                typeDao.insert(*it.toTypedArray())
            }

        entity.toTable()
            .also { pokemon ->
                pokemonDao.insert(pokemon)
            }
    }
}
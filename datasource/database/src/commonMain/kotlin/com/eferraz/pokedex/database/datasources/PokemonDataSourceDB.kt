package com.eferraz.pokedex.database.datasources

import com.eferraz.pokedex.database.daos.PokemonDao
import com.eferraz.pokedex.database.daos.TypeDao
import com.eferraz.pokedex.database.helpers.toTable
import com.eferraz.pokedex.database.helpers.toVO
import com.eferraz.pokedex.entity.PokemonVO
import com.eferraz.pokedex.repositories.datasources.PokemonDataSource

internal class PokemonDataSourceDB(
    private val pokemonDao: PokemonDao,
    private val typeDao: TypeDao,
) : PokemonDataSource.Database {

    override suspend fun get(id: Int): PokemonVO? =
        pokemonDao.get(id)?.toVO()

    override suspend fun insert(entity: PokemonVO) {

        listOfNotNull(entity.type1, entity.type2)
            .map { it.toTable() }
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
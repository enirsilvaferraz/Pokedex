package com.example.pokedex.database.contracts

import com.example.pokedex.database.daos.PokemonDao
import com.example.pokedex.database.daos.TypeDao
import com.example.pokedex.database.entities.PokemonTable
import com.example.pokedex.database.entities.TypeTable
import com.example.pokedex.database.relationships.PokemonAndType
import com.example.pokedex.entity.PokemonVO
import com.example.pokedex.entity.TypeVO
import com.example.repositories.datasources.PokemonDataSource
import kotlinx.coroutines.flow.map

internal class PokemonDataSourceDB(
    private val pokemonDao: PokemonDao,
    private val typeDao: TypeDao,
) : PokemonDataSource.Database {

    override suspend fun getAll() =
        pokemonDao.getAll().map { it.map { it.toVo() } }

    override suspend fun insert(entities: List<PokemonVO>) {
        pokemonDao.insert(*entities.map { it.toTable() }.toTypedArray())
    }

    override suspend fun update(vararg entities: PokemonVO) {

        val types = (entities.mapNotNull { it.type1 } + entities.mapNotNull { it.type2 }).map { it.toTable() }
        types.distinctBy { it.id }.also {
            typeDao.insertAll(*it.toTypedArray())
        }

        entities.map { it.toTable() }.also { pokemon ->
            pokemonDao.update(*pokemon.toTypedArray())
        }
    }

    private fun TypeTable.transform() = TypeVO(
        id = id,
        name = name
    )

    private fun PokemonVO.toTable() = PokemonTable(
        id = id,
        name = name,
        image = image,
        typeID1 = type1?.id,
        typeID2 = type2?.id
    )

    private fun TypeVO.toTable() = TypeTable(
        id = id,
        name = name
    )

    private fun PokemonAndType.toVo() = PokemonVO(
        id = pokemon.id,
        name = pokemon.name,
        image = pokemon.image,
        type1 = type1?.transform(),
        type2 = type2?.transform()
    )
}
package com.example.pokedex.database.contracts

import com.example.pokedex.database.daos.PokemonDao
import com.example.pokedex.database.daos.TypeDao
import com.example.pokedex.database.entities.PokemonTable
import com.example.pokedex.database.entities.TypeTable
import com.example.pokedex.database.relationships.PokemonAndType
import com.example.pokedex.entity.PokemonVO
import com.example.pokedex.entity.TypeVO
import com.example.repositories.datasources.ReadableDataSource
import com.example.repositories.datasources.WritableDataSource

internal class PokemonDataSourceDB(
    private val pokemonDao: PokemonDao,
    private val typeDao: TypeDao,
) : ReadableDataSource<PokemonVO>, WritableDataSource<PokemonVO> {

    override suspend fun get(limit: Int, offset: Int) =
        pokemonDao.get(limit, offset).map { it.toVo() }

    override suspend fun insert(entities: List<PokemonVO>) {

        val types1 = entities.map { it.type1.toTable() }
        val types2 = entities.mapNotNull { it.type2?.toTable() }

        val types = types1 + types2

        types.distinctBy { it.id }.also {
            typeDao.insertAll(*it.toTypedArray())
        }

        entities.map { it.toTable() }.also { pokemon ->
            pokemonDao.insertAll(*pokemon.toTypedArray())
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
        typeID1 = type1.id,
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
        type1 = type1.transform(),
        type2 = type2?.transform()
    )
}
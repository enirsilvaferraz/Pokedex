package com.example.pokedex.database.contracts

import com.example.pokedex.database.daos.PokemonDao
import com.example.pokedex.database.daos.PokemonTypeDao
import com.example.pokedex.database.daos.TypeDao
import com.example.pokedex.database.entities.PokemonTable
import com.example.pokedex.database.entities.PokemonTypeTable
import com.example.pokedex.database.entities.TypeTable
import com.example.pokedex.database.relationships.PokemonWithTypes
import com.example.pokedex.entity.PokemonVO
import com.example.pokedex.entity.TypeVO
import com.example.repositories.datasources.ReadableDataSource
import com.example.repositories.datasources.WritableDataSource
import kotlinx.coroutines.flow.flow

internal class PokemonDataSourceDB(
    private val pokemonDao: PokemonDao,
    private val typeDao: TypeDao,
    private val pokemonTypeDao: PokemonTypeDao,
) : ReadableDataSource<PokemonVO>, WritableDataSource<PokemonVO> {

    override suspend fun getAll() = flow {
        emit(pokemonDao.getAll().map { it.transform() })
    }

    override suspend fun insert(entities: List<PokemonVO>) {

        val pokemonWithTypesList = entities.map { it.transform() }

        val pokemonTables = pokemonWithTypesList.map { it.pokemon }
        pokemonDao.insertAll(*pokemonTables.toTypedArray())

        val allTypes = pokemonWithTypesList.flatMap { it.types }.distinctBy { it.id }
        typeDao.insertAll(*allTypes.toTypedArray())

        pokemonTypeDao.insertAll(
            *pokemonWithTypesList
                .map { (pokemon, types) ->
                    types.map { type ->
                        Pair(pokemon.id, type.id)
                    }
                }.flatMap {
                    it.map {
                        PokemonTypeTable(it.first, it.second)
                    }
                }.toTypedArray()
        )
    }

    private fun PokemonWithTypes.transform(): PokemonVO = PokemonVO(
        id = pokemon.id,
        name = pokemon.name,
        image = pokemon.image,
        types = types.map { it.transform() }
    )

    private fun TypeTable.transform() = TypeVO(
        id = id,
        name = name
    )

    private fun PokemonVO.transform(): PokemonWithTypes =
        PokemonWithTypes(
            pokemon = PokemonTable(
                id = id,
                name = name,
                image = image
            ),
            types = types.map { it.transform() }
        )

    private fun TypeVO.transform() = TypeTable(
        id = id,
        name = name
    )
}

package com.example.pokedex.database.contracts

import com.example.pokedex.database.daos.PokemonDao
import com.example.pokedex.database.relationships.PokemonWithTypes
import com.example.pokedex.entity.PokemonVO
import com.example.repositories.datasources.ReadableDataSource
import kotlinx.coroutines.flow.map

internal class PokemonDataSourceDB(
    private val dao: PokemonDao
) : ReadableDataSource<PokemonVO> {

    override suspend fun getAll() = dao.getAll().map(::transform)

    private fun transform(value: List<PokemonWithTypes>) = value.map(::transform)

    private fun transform(value: PokemonWithTypes) = PokemonVO(
        id = value.pokemon.id,
        name = value.pokemon.name,
        image = value.pokemon.image,
        types = value.types.map { it.name }
    )
}
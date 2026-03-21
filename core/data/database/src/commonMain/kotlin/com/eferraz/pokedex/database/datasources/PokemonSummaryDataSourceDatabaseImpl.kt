package com.eferraz.pokedex.database.datasources

import com.eferraz.pokedex.database.daos.PokemonSummaryDao
import com.eferraz.pokedex.database.entities.PokemonSummaryTable.Companion.toRefTable
import com.eferraz.pokedex.entity.BasePokemon
import com.eferraz.pokedex.entity.summary.PokemonSummary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
internal class PokemonSummaryDataSourceDatabaseImpl(
    private val dao: PokemonSummaryDao,
) : PokemonSummaryDataSourceDatabase {

    override fun get(limit: Int, offset: Int) =
        dao.get(limit = limit, offset = offset).map { it.map { it.toModel() } }

    override suspend fun upsert(vararg pokemons: PokemonSummary) {
        dao.upsert(*pokemons.map { it.toRefTable() }.toTypedArray())
    }
}
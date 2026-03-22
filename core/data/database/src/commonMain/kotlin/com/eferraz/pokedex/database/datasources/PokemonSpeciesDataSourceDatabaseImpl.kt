package com.eferraz.pokedex.database.datasources

import com.eferraz.pokedex.database.daos.BreedingDao
import com.eferraz.pokedex.database.daos.BreedingWithEggGroupsDao
import com.eferraz.pokedex.database.daos.EggGroupDao
import com.eferraz.pokedex.database.daos.PokemonDetailedDao
import com.eferraz.pokedex.database.daos.SpeciesDao
import com.eferraz.pokedex.database.entities.BreedingEggGroupCrossRef.Companion.toCrossRef
import com.eferraz.pokedex.database.entities.BreedingTable.Companion.toTable
import com.eferraz.pokedex.database.entities.EggGroupTable.Companion.toTable
import com.eferraz.pokedex.database.entities.SpeciesTable.Companion.toTable
import com.eferraz.pokedex.entity.detail.Species
import org.koin.core.annotation.Factory


@Factory
internal class PokemonSpeciesDataSourceDatabaseImpl(
    private val speciesDao: SpeciesDao,
    private val breedingDao: BreedingDao,
    private val eggGroupDao: EggGroupDao,
    private val breedingEggGroupDao: BreedingWithEggGroupsDao,
    private val detailedDao: PokemonDetailedDao,
) : PokemonSpeciesDataSourceDatabase {

    override suspend fun upsert(entity: Species) {

        entity
            .toTable()
            .let { speciesDao.upsert(it) }

        entity.breeding.eggGroups
            .map { it.toTable() }
            .let { eggGroupDao.upsert(*it.toTypedArray()) }

        entity.breeding
            .toTable()
            .let { breedingDao.upsert(it) }

        entity.breeding
            .toCrossRef()
            .let { breedingEggGroupDao.upsert(*it.toTypedArray()) }

        detailedDao.updateSpeciesFk(pokemonId = entity.id, speciesId = entity.id)
    }
}
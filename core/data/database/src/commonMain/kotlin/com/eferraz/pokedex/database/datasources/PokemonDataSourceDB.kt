package com.eferraz.pokedex.database.datasources

import androidx.room.immediateTransaction
import androidx.room.useWriterConnection
import com.eferraz.pokedex.database.core.AppDatabase
import com.eferraz.pokedex.database.daos.AbilityDao
import com.eferraz.pokedex.database.daos.AboutDao
import com.eferraz.pokedex.database.daos.AboutWithAbilitiesDao
import com.eferraz.pokedex.database.daos.BreedingDao
import com.eferraz.pokedex.database.daos.BreedingWithEggGroupsDao
import com.eferraz.pokedex.database.daos.EggGroupDao
import com.eferraz.pokedex.database.daos.MoveDao
import com.eferraz.pokedex.database.daos.PokemonDao
import com.eferraz.pokedex.database.daos.PokemonWithMovesDao
import com.eferraz.pokedex.database.daos.StatsDao
import com.eferraz.pokedex.database.daos.TypeDao
import com.eferraz.pokedex.database.entities.AbilityTable.Companion.toTable
import com.eferraz.pokedex.database.entities.AboutAbilitiesCrossRef.Companion.toCrossRef
import com.eferraz.pokedex.database.entities.AboutTable.Companion.toTable
import com.eferraz.pokedex.database.entities.BreedingEggGroupCrossRef.Companion.toCrossRef
import com.eferraz.pokedex.database.entities.BreedingTable.Companion.toTable
import com.eferraz.pokedex.database.entities.EggGroupTable.Companion.toTable
import com.eferraz.pokedex.database.entities.MoveTable.Companion.toTable
import com.eferraz.pokedex.database.entities.PokemonMovesCrossRef.Companion.toCrossRef
import com.eferraz.pokedex.database.entities.PokemonTable.Companion.toTable
import com.eferraz.pokedex.database.entities.StatsTable.Companion.toTable
import com.eferraz.pokedex.database.entities.TypeTable.Companion.toTable
import com.eferraz.pokedex.entity.PokemonCompleteVO
import com.eferraz.pokedex.repositories.datasources.PokemonDataSource
import org.koin.core.annotation.Factory

@Factory([PokemonDataSource.Database::class])
internal class PokemonDataSourceDB(
    private val room: AppDatabase,
    private val pokemonDao: PokemonDao,
    private val typeDao: TypeDao,
    private val aboutDao: AboutDao,
    private val abilityDao: AbilityDao,
    private val aboutWithAbilitiesDao: AboutWithAbilitiesDao,
    private val breedingDao: BreedingDao,
    private val eggGroupDao: EggGroupDao,
    private val breedingEggGroupDao: BreedingWithEggGroupsDao,
    private val statsDao: StatsDao,
    private val moveDao: MoveDao,
    private val pokemonWithMovesDao: PokemonWithMovesDao,
) : PokemonDataSource.Database {

    override suspend fun getComplete(id: Int) =
        pokemonDao.getComplete(id)?.toModel()

    override suspend fun getLight(id: Int) =
        pokemonDao.getLight(id)?.toModel()

    override suspend fun insert(entity: PokemonCompleteVO) {

        room.useWriterConnection { transactor ->

            transactor.immediateTransaction {

                entity.about.abilities
                    .map { it.toTable() }
                    .let { abilityDao.insert(*it.toTypedArray()) }

                entity.about
                    .toTable()
                    .let { aboutDao.insert(it) }

                entity.about
                    .toCrossRef()
                    .let { aboutWithAbilitiesDao.insert(*it.toTypedArray()) }

                entity.breeding.eggGroups
                    .map { it.toTable() }
                    .let { eggGroupDao.insert(*it.toTypedArray()) }

                entity.breeding
                    .toTable()
                    .let { breedingDao.insert(it) }

                entity.breeding
                    .toCrossRef()
                    .let { breedingEggGroupDao.insert(*it.toTypedArray()) }

                entity.types()
                    .map { it.toTable() }
                    .let { typeDao.insert(*it.toTypedArray()) }

                entity.stats
                    .toTable()
                    .let { statsDao.insert(it) }

                entity.moves
                    .map { it.toTable() }
                    .let { moveDao.insert(*it.toTypedArray()) }

                entity
                    .toTable()
                    .let { pokemonDao.insert(it) }

                entity
                    .toCrossRef()
                    .let { pokemonWithMovesDao.insert(*it.toTypedArray()) }

            }
        }
    }
}
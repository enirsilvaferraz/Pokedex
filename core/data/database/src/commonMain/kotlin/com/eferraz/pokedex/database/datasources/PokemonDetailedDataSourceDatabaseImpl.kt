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
import com.eferraz.pokedex.database.daos.PokemonDetailedDao
import com.eferraz.pokedex.database.daos.PokemonSummaryDao
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
import com.eferraz.pokedex.database.entities.PokemonDetailedTable.Companion.toDetailTable
import com.eferraz.pokedex.database.entities.PokemonMovesCrossRef.Companion.toCrossRef
import com.eferraz.pokedex.database.entities.PokemonSummaryTable.Companion.toRefTable
import com.eferraz.pokedex.database.entities.StatsTable.Companion.toTable
import com.eferraz.pokedex.database.entities.TypeTable.Companion.toTable
import com.eferraz.pokedex.entity.detail.PokemonDetailed
import org.koin.core.annotation.Factory

@Factory([PokemonDetailedDataSourceDatabase::class])
internal class PokemonDetailedDataSourceDatabaseImpl(
    private val room: AppDatabase,
    private val summaryDao: PokemonSummaryDao,
    private val detailedDao: PokemonDetailedDao,
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
) : PokemonDetailedDataSourceDatabase {

    override suspend fun get(id: Long) =
        detailedDao.get(id)?.toModel()

    override suspend fun upsert(entity: PokemonDetailed) {

        room.useWriterConnection { transactor ->

            transactor.immediateTransaction {

                entity
                    .toRefTable()
                    .let { summaryDao.upsert(it) }

                entity.about.abilities
                    .map { it.toTable() }
                    .let { abilityDao.upsert(*it.toTypedArray()) }

                entity.about
                    .toTable()
                    .let { aboutDao.upsert(it) }

                entity.about
                    .toCrossRef()
                    .let { aboutWithAbilitiesDao.upsert(*it.toTypedArray()) }

                entity.breeding.eggGroups
                    .map { it.toTable() }
                    .let { eggGroupDao.upsert(*it.toTypedArray()) }

                entity.breeding
                    .toTable()
                    .let { breedingDao.upsert(it) }

                entity.breeding
                    .toCrossRef()
                    .let { breedingEggGroupDao.upsert(*it.toTypedArray()) }

                entity.types()
                    .map { it.toTable() }
                    .let { typeDao.upsert(*it.toTypedArray()) }

                entity.stats
                    .toTable()
                    .let { statsDao.upsert(it) }

                entity.moves
                    .map { it.toTable() }
                    .let { moveDao.upsert(*it.toTypedArray()) }

                entity
                    .toDetailTable()
                    .let { detailedDao.upsert(it) }

                entity
                    .toCrossRef()
                    .let { pokemonWithMovesDao.upsert(*it.toTypedArray()) }
            }
        }
    }
}
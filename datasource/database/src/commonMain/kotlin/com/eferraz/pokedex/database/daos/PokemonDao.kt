package com.eferraz.pokedex.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.eferraz.pokedex.database.entities.AbilityTable
import com.eferraz.pokedex.database.entities.AbilityTable.Companion.toTable
import com.eferraz.pokedex.database.entities.*
import com.eferraz.pokedex.database.entities.AboutAbilitiesCrossRef.Companion.toCrossRef
import com.eferraz.pokedex.database.entities.AboutTable
import com.eferraz.pokedex.database.entities.AboutTable.Companion.toTable
import com.eferraz.pokedex.database.entities.BreedingEggGroupCrossRef.Companion.toCrossRef
import com.eferraz.pokedex.database.entities.BreedingTable.Companion.toTable
import com.eferraz.pokedex.database.entities.EggGroupTable.Companion.toTable
import com.eferraz.pokedex.database.entities.MoveTable.Companion.toTable
import com.eferraz.pokedex.database.entities.PokemonMovesCrossRef.Companion.toCrossRef
import com.eferraz.pokedex.database.entities.PokemonTable
import com.eferraz.pokedex.database.entities.PokemonTable.Companion.toTable
import com.eferraz.pokedex.database.entities.StatsTable.Companion.toTable
import com.eferraz.pokedex.database.entities.TypeTable.Companion.toTable
import com.eferraz.pokedex.database.relationships.PokemonComplete
import com.eferraz.pokedex.database.relationships.PokemonLight
import com.eferraz.pokedex.entity.PokemonCompleteVO

@Dao
internal interface PokemonDao {

    @Query("SELECT * FROM Pokemon WHERE pokemon_id = :id")
    suspend fun getLight(id: Int): PokemonLight?

    @Query("SELECT * FROM Pokemon WHERE pokemon_id = :id")
    suspend fun getComplete(id: Int): PokemonComplete?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg entity: PokemonTable)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg entity: AbilityTable)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg entity: AboutTable)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg entity: AboutAbilitiesCrossRef)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg entity: BreedingTable)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg entity: BreedingEggGroupCrossRef)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg entity: EggGroupTable)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg entity: MoveTable)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg entity: PokemonMovesCrossRef)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg entity: StatsTable)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg entity: TypeTable)

    @Transaction
    suspend fun insert(entity: PokemonCompleteVO) {

        entity.about.abilities
            .map { it.toTable() }
            .let { insert(*it.toTypedArray()) }

        entity.about
            .toTable()
            .let { insert(it) }

        entity.about
            .toCrossRef()
            .let { insert(*it.toTypedArray()) }

        entity.breeding.eggGroups
            .map { it.toTable() }
            .let { insert(*it.toTypedArray()) }

        entity.breeding
            .toTable()
            .let { insert(it) }

        entity.breeding
            .toCrossRef()
            .let { insert(*it.toTypedArray()) }

        entity.types()
            .map { it.toTable() }
            .let { insert(*it.toTypedArray()) }

        entity.stats
            .toTable()
            .let { insert(it) }

        entity.moves
            .map { it.toTable() }
            .let { insert(*it.toTypedArray()) }

        entity
            .toTable()
            .let { insert(it) }

        entity
            .toCrossRef()
            .let { insert(*it.toTypedArray()) }
    }
}
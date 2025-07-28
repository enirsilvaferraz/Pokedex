package com.eferraz.pokedex.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eferraz.pokedex.database.entities.AboutTable
import com.eferraz.pokedex.database.entities.BreedingTable
import com.eferraz.pokedex.database.entities.StatsTable
import com.eferraz.pokedex.database.entities.TypeTable

@Dao
internal interface StatsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg entity: StatsTable)

    @Query("SELECT * FROM stats WHERE stats_id = :id")
    suspend fun getById(id: Long): StatsTable?
}

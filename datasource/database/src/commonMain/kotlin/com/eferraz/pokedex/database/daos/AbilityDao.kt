package com.eferraz.pokedex.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eferraz.pokedex.database.entities.AbilityTable
import com.eferraz.pokedex.database.entities.EggGroupTable

@Dao
internal interface AbilityDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg entity: AbilityTable)

    @Query("SELECT * FROM ability WHERE ability_id = :id")
    suspend fun getById(id: Long): AbilityTable?
}

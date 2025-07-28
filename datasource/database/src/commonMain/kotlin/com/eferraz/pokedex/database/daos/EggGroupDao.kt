package com.eferraz.pokedex.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eferraz.pokedex.database.entities.EggGroupTable

@Dao
internal interface EggGroupDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg entity: EggGroupTable)

    @Query("SELECT * FROM egg_group WHERE egg_group_id = :id")
    suspend fun getById(id: Long): EggGroupTable?
}

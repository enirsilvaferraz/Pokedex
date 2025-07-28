package com.eferraz.pokedex.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eferraz.pokedex.database.entities.AboutTable
import com.eferraz.pokedex.database.entities.TypeTable

@Dao
internal interface AboutDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg entity: AboutTable)

    @Query("SELECT * FROM about WHERE about_id = :id")
    suspend fun getById(id: Long): AboutTable?
}

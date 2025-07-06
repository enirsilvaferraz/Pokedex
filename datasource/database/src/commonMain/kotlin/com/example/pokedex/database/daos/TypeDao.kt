package com.example.pokedex.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedex.database.entities.TypeTable

@Dao
internal interface TypeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg entity: TypeTable)

    @Query("SELECT * FROM type WHERE type_id = :id")
    suspend fun getById(id: Long): TypeTable?
}

package com.example.pokedex.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pokedex.database.entities.Pokedex

@Dao
internal interface PokedexDao {

    @Insert
    suspend fun insert(pokedex: Pokedex)

    @Query("SELECT * FROM Pokedex")
    suspend fun getAll(): List<Pokedex>
}
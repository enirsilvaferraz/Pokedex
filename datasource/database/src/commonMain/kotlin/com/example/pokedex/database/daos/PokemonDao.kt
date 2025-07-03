package com.example.pokedex.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedex.database.entities.PokemonTable
import com.example.pokedex.database.relationships.PokemonAndType

@Dao
internal interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg entity: PokemonTable)

    @Query("SELECT * FROM Pokemon")
    suspend fun getAll(): List<PokemonAndType>

    @Query("SELECT * FROM Pokemon LIMIT :limit OFFSET :offset")
    suspend fun get(limit: Int, offset: Int): List<PokemonAndType>
}
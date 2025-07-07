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
    suspend fun insert(vararg entity: PokemonTable)

    @Query("SELECT * FROM Pokemon WHERE pokemon_id = :id")
    suspend fun get(id: Int): PokemonAndType?
}
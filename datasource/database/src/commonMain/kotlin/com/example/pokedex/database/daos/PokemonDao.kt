package com.example.pokedex.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pokedex.database.entities.PokemonTable
import com.example.pokedex.database.relationships.PokemonWithTypes
import kotlinx.coroutines.flow.Flow

@Dao
internal interface PokemonDao {

    @Insert
    fun insert(entity: PokemonTable)

    @Query("SELECT * FROM Pokemon")
    fun getAll(): Flow<List<PokemonWithTypes>>
}
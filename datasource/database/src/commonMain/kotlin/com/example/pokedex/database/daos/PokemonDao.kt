package com.example.pokedex.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedex.database.entities.PokemonTable
import com.example.pokedex.database.relationships.PokemonWithTypes
import kotlinx.coroutines.flow.Flow

@Dao
internal interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend  fun insertAll(vararg entity: PokemonTable)

    @Query("SELECT * FROM Pokemon")
    suspend   fun getAll(): List<PokemonWithTypes>
}
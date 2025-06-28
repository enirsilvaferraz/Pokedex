package com.example.pokedex.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.pokedex.database.entities.PokemonTypeTable

@Dao
internal interface PokemonTypeDao {

    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    suspend  fun insertAll(vararg entity: PokemonTypeTable)
}
package com.eferraz.pokedex.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eferraz.pokedex.database.entities.PokemonTable
import com.eferraz.pokedex.database.relationships.PokemonComplete
import com.eferraz.pokedex.database.relationships.PokemonLight

@Dao
internal interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg entity: PokemonTable)

    @Query("SELECT * FROM Pokemon WHERE pokemon_id = :id")
    suspend fun getLight(id: Int): PokemonLight?

    @Query("SELECT * FROM Pokemon WHERE pokemon_id = :id")
    suspend fun getComplete(id: Int): PokemonComplete?
}
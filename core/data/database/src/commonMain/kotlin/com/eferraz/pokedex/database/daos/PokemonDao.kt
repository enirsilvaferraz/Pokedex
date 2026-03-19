package com.eferraz.pokedex.database.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.eferraz.pokedex.database.entities.PokemonTable
import com.eferraz.pokedex.database.relationships.PokemonComplete
import com.eferraz.pokedex.database.relationships.PokemonLight

@Dao
internal interface PokemonDao : BaseDao<PokemonTable> {

    @Transaction
    @Query("SELECT * FROM Pokemon WHERE pokemon_id = :id")
    suspend fun getLight(id: Int): PokemonLight?

    @Transaction
    @Query("SELECT * FROM Pokemon WHERE pokemon_id = :id")
    suspend fun getComplete(id: Int): PokemonComplete?
}
package com.eferraz.pokedex.database.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.eferraz.pokedex.database.entities.PokemonDetailedTable
import com.eferraz.pokedex.database.relationships.PokemonComplete

@Dao
internal abstract class PokemonDetailedDao : BaseDao<PokemonDetailedTable> {

    @Transaction
    @Query("SELECT * FROM pokemon_detail WHERE pokemon_id = :id")
    abstract suspend fun get(id: Long): PokemonComplete?
}

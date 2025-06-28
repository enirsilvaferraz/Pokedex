package com.example.pokedex.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "pokemon_type", primaryKeys = ["pokemon_id", "type_id"])
internal data class PokemonTypeTable(

    @ColumnInfo(name = "pokemon_id")
    val pokemonId: Long,

    @ColumnInfo(name = "type_id")
    val typeId: Long
)
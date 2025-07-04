package com.example.pokedex.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "pokemon",
    foreignKeys = [
        ForeignKey(
            entity = TypeTable::class,
            parentColumns = ["type_id"],
            childColumns = ["type1"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = TypeTable::class,
            parentColumns = ["type_id"],
            childColumns = ["type2"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
    ]
)
internal data class PokemonTable(

    @PrimaryKey
    @ColumnInfo(name = "pokemon_id")
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "type1")
    val typeID1: Long? = null,

    @ColumnInfo(name = "type2")
    val typeID2: Long? = null,
)


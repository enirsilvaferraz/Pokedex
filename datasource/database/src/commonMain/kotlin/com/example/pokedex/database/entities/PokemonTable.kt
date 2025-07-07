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
    val typeID1: Long,

    @ColumnInfo(name = "type2")
    val typeID2: Long? = null,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "weight")
    val weight: Float,

    @ColumnInfo(name = "height")
    val height: Float,

    @ColumnInfo(name = "category")
    val category: String,

//    @ColumnInfo(name = "abilities")
//    val abilities: List<String>,

    @ColumnInfo(name = "gender_ratio")
    val genderRatio: Float, // e.g., 0.875 for 87.5% male

//    @ColumnInfo(name = "stats")
//    val stats: List<StatTable>,

    @ColumnInfo(name = "primary_color")
    val primaryColor: String,
)


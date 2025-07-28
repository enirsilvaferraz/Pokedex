package com.eferraz.pokedex.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eferraz.pokedex.entity.AboutVO

@Entity(tableName = "about")
internal data class AboutTable(

    @PrimaryKey
    @ColumnInfo(name = "about_id")
    val id: Long,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "genera")
    val species: String,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "weight")
    val weight: Float,

    @ColumnInfo(name = "height")
    val height: Float
) {

    companion object {
        internal fun AboutVO.toTable() = AboutTable(
            id = id,
            description = description,
            species = species,
            category = category,
            height = height,
            weight = weight
        )
    }
}

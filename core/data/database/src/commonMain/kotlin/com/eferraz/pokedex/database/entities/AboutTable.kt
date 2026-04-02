package com.eferraz.pokedex.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eferraz.pokedex.entity.detail.About

@Entity(tableName = "about")
internal data class AboutTable(

    @PrimaryKey
    @ColumnInfo(name = "about_id")
    val id: Long,

    @ColumnInfo(name = "weight")
    val weight: Float,

    @ColumnInfo(name = "height")
    val height: Float,
) {

    fun toModel(abilities: List<AbilityTable>): About =
        About(
            id = id,
            height = height,
            weight = weight,
            abilities = abilities.map { it.toModel() },
        )

    companion object {
        internal fun About.toTable() =
            AboutTable(
                id = id,
                height = height,
                weight = weight,
            )
    }
}

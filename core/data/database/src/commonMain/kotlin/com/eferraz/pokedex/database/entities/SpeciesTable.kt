package com.eferraz.pokedex.database.entities

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.eferraz.pokedex.entity.detail.Breeding
import com.eferraz.pokedex.entity.detail.Species

@Entity(tableName = "species")
internal data class SpeciesTable(

    @PrimaryKey
    @ColumnInfo(name = "species_id")
    val id: Long,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "genera")
    val species: String,

    @ColumnInfo(name = "category")
    val category: String,
) {

    fun toModel(breeding: Breeding): Species =
        Species(
            id = id,
            description = description,
            species = species,
            category = category,
            breeding = breeding,
        )

    companion object {
        internal fun Species.toTable() =
            SpeciesTable(
                id = id,
                description = description,
                species = species,
                category = category,
            )
    }
}

package com.eferraz.pokedex.database.entities

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey
import com.eferraz.pokedex.entity.detail.Breeding

@Entity(
    tableName = "breeding",
    foreignKeys = [
        ForeignKey(
            entity = SpeciesTable::class,
            parentColumns = ["species_id"],
            childColumns = ["species_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
    ],
    indices = [Index(value = ["species_id"])],
)
internal data class BreedingTable(

    @PrimaryKey
    @ColumnInfo(name = "species_id")
    val speciesId: Long,

    @ColumnInfo(name = "gender_ratio")
    val genderRatio: Float,
) {

    fun toModel(eggGroups: List<EggGroupTable>): Breeding =
        Breeding(
            id = speciesId,
            genderRatio = genderRatio,
            eggGroups = eggGroups.map { it.toModel() },
        )

    companion object {
        internal fun Breeding.toTable() =
            BreedingTable(
                speciesId = id,
                genderRatio = genderRatio,
            )
    }
}

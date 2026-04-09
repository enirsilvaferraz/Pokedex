package com.eferraz.pokedex.database.entities

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import com.eferraz.pokedex.entity.detail.Breeding

@Entity(
    tableName = "breeding_egg_groups",
    primaryKeys = ["species_id", "egg_group_id"],
    indices = [
        Index(value = ["egg_group_id"]),
    ],
    foreignKeys = [
        ForeignKey(
            entity = BreedingTable::class,
            parentColumns = ["species_id"],
            childColumns = ["species_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = EggGroupTable::class,
            parentColumns = ["egg_group_id"],
            childColumns = ["egg_group_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
    ],
)
internal data class BreedingEggGroupCrossRef(

    @ColumnInfo(name = "species_id")
    val speciesId: Long,

    @ColumnInfo(name = "egg_group_id")
    val eggGroupId: Long,
) {

    companion object {
        internal fun Breeding.toCrossRef() =
            this.eggGroups.map {
                BreedingEggGroupCrossRef(
                    speciesId = this.id,
                    eggGroupId = it.id,
                )
            }
    }
}

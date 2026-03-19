package com.eferraz.pokedex.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.eferraz.pokedex.entity.Breeding

@Entity(
    tableName = "breeding_egg_groups",
    primaryKeys = ["breeding_id", "egg_group_id"],
    indices = [
        Index(value = ["egg_group_id"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = BreedingTable::class,
            parentColumns = ["breeding_id"],
            childColumns = ["breeding_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = EggGroupTable::class,
            parentColumns = ["egg_group_id"],
            childColumns = ["egg_group_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
    ]
)
internal data class BreedingEggGroupCrossRef(

    @ColumnInfo(name = "breeding_id")
    val breedingId: Long,

    @ColumnInfo(name = "egg_group_id")
    val eggGroupId: Long,
) {

    companion object {
        internal fun Breeding.toCrossRef() = this.eggGroups.map {
            BreedingEggGroupCrossRef(
                breedingId = this.id,
                eggGroupId = it.id
            )
        }
    }
}
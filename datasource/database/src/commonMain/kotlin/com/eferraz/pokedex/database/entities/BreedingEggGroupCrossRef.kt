package com.eferraz.pokedex.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.eferraz.pokedex.entity.AboutVO
import com.eferraz.pokedex.entity.BreedingVO

@Entity(
    tableName = "breeding_egg_groups",
    primaryKeys = ["breeding_id", "egg_group_id"],
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
        internal fun BreedingVO.toCrossRef() = this.eggGroups.map {
            BreedingEggGroupCrossRef(
                breedingId = this@toCrossRef.id,
                eggGroupId = id
            )
        }
    }
}
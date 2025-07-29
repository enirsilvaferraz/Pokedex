package com.eferraz.pokedex.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.eferraz.pokedex.entity.AboutVO

@Entity(
    tableName = "about_abilities",
    primaryKeys = ["about_id", "ability_id"],
    foreignKeys = [
        ForeignKey(
            entity = AboutTable::class,
            parentColumns = ["about_id"],
            childColumns = ["about_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AbilityTable::class,
            parentColumns = ["ability_id"],
            childColumns = ["ability_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
    ]
)
internal data class AboutAbilitiesCrossRef(

    @ColumnInfo(name = "about_id", index = true)
    val aboutId: Long,

    @ColumnInfo(name = "ability_id", index = true)
    val abilityId: Long,
) {

    companion object {
        internal fun AboutVO.toCrossRef() = this.abilities.map {
            AboutAbilitiesCrossRef(
                aboutId = this.id,
                abilityId = it.id
            )
        }
    }
}
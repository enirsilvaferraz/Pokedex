package com.eferraz.pokedex.database.entities

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.ForeignKey
import com.eferraz.pokedex.entity.detail.About

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
        internal fun About.toCrossRef() =
            this.abilities.map {
                AboutAbilitiesCrossRef(
                    aboutId = this.id,
                    abilityId = it.id
                )
            }
    }
}

package com.eferraz.pokedex.database.entities

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.eferraz.pokedex.entity.detail.Ability

@Entity(tableName = "ability")
internal data class AbilityTable(

    @PrimaryKey
    @ColumnInfo(name = "ability_id")
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String,
) {

    fun toModel(): Ability =
        Ability(
            id = id,
            name = name
        )

    companion object {
        internal fun Ability.toTable() =
            AbilityTable(
                id = id,
                name = name
            )
    }
}

package com.eferraz.pokedex.database.entities

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.eferraz.pokedex.entity.detail.EggGroup

@Entity(tableName = "egg_group")
internal data class EggGroupTable(

    @PrimaryKey
    @ColumnInfo(name = "egg_group_id")
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String,
) {

    fun toModel(): EggGroup =
        EggGroup(
            id = id,
            name = name
        )

    companion object {
        internal fun EggGroup.toTable() =
            EggGroupTable(
                id = id,
                name = name,
            )
    }
}

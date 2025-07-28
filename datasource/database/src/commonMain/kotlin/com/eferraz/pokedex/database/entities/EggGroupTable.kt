package com.eferraz.pokedex.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eferraz.pokedex.entity.EggGroupVO

@Entity(tableName = "egg_group")
internal data class EggGroupTable(

    @PrimaryKey
    @ColumnInfo(name = "egg_group_id")
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String,
) {

    fun toModel(): EggGroupVO = EggGroupVO(
        id = id,
        name = name
    )

    companion object {
        internal fun EggGroupVO.toTable() = EggGroupTable(
            id = id,
            name = name,
        )
    }
}
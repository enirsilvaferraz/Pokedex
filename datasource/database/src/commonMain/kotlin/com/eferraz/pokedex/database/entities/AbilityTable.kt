package com.eferraz.pokedex.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eferraz.pokedex.entity.AbilityVO
import com.eferraz.pokedex.entity.AboutVO

@Entity(tableName = "ability")
internal data class AbilityTable(

    @PrimaryKey
    @ColumnInfo(name = "ability_id")
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String,
) {

    fun toModel(): AbilityVO = AbilityVO(
        id = id,
        name = name
    )

    companion object {
        internal fun AbilityVO.toTable() = AbilityTable(
            id = id,
            name = name
        )
    }
}
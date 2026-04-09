package com.eferraz.pokedex.database.entities

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.eferraz.pokedex.entity.detail.Move

@Entity(tableName = "moves")
internal data class MoveTable(

    @PrimaryKey
    @ColumnInfo(name = "move_id")
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String,
) {

    fun toModel(): Move =
        Move(
            id = id,
            name = name
        )

    companion object {
        internal fun Move.toTable() =
            MoveTable(
                id = id,
                name = name
            )
    }
}

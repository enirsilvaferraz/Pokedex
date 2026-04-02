package com.eferraz.pokedex.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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

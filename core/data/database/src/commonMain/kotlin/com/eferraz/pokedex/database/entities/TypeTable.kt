package com.eferraz.pokedex.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eferraz.pokedex.entity.Type

@Entity("type")
internal data class TypeTable(

    @PrimaryKey
    @ColumnInfo(name = "type_id")
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String,
) {

    companion object {

        internal fun Type.toTable() = TypeTable(
            id = id,
            name = name
        )

        fun TypeTable.toModel(): Type = Type(
            id = id,
            name = name
        )
    }
}

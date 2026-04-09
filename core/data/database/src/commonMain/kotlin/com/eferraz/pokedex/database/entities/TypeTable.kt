package com.eferraz.pokedex.database.entities

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.eferraz.pokedex.entity.detail.Type

@Entity("type")
internal data class TypeTable(

    @PrimaryKey
    @ColumnInfo(name = "type_id")
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String,
) {

    companion object {

        internal fun Type.toTable() =
            TypeTable(
                id = id,
                name = name
            )

        fun TypeTable.toModel(): Type =
            Type(
                id = id,
                name = name
            )
    }
}

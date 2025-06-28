package com.example.pokedex.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("type")
internal data class TypeTable(

    @PrimaryKey
    @ColumnInfo(name = "type_id")
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String
)
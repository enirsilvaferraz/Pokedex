package com.eferraz.pokedex.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eferraz.pokedex.entity.BreedingVO

@Entity(tableName = "breeding",)
internal data class BreedingTable(

    @PrimaryKey
    @ColumnInfo(name = "breeding_id")
    val id: Long,

    @ColumnInfo(name = "male_ratio")
    val maleRatio: Float, // e.g., 0.875 for 87.5% male
) {

    companion object {
        internal fun BreedingVO.toTable() = BreedingTable(
            id = id,
            maleRatio = male
        )
    }
}
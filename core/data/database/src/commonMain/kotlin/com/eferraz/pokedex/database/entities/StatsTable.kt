package com.eferraz.pokedex.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eferraz.pokedex.entity.StatsVO

@Entity(tableName = "stats")
internal data class StatsTable(

    @PrimaryKey
    @ColumnInfo(name = "stats_id")
    val id: Long,

    @ColumnInfo(name = "hp")
    val hp: Int,

    @ColumnInfo(name = "attack")
    val attack: Int,

    @ColumnInfo(name = "defense")
    val defense: Int,

    @ColumnInfo(name = "special_attack")
    val specialAttack: Int,

    @ColumnInfo(name = "special_defense")
    val specialDefense: Int,

    @ColumnInfo(name = "speed")
    val speed: Int
) {

    fun toModel(): StatsVO = StatsVO(
        id = id,
        hp = hp,
        attack = attack,
        defense = defense,
        specialAttack = specialAttack,
        specialDefense = specialDefense,
        speed = speed
    )

    companion object {
        internal fun StatsVO.toTable() = StatsTable(
            id = id,
            hp = hp,
            attack = attack,
            defense = defense,
            specialAttack = specialAttack,
            specialDefense = specialDefense,
            speed = speed
        )
    }
}

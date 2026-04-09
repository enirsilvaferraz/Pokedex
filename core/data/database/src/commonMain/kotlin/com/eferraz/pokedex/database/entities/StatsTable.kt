package com.eferraz.pokedex.database.entities

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.eferraz.pokedex.entity.detail.Stats

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

    fun toModel(): Stats =
        Stats(
            id = id,
            hp = hp,
            attack = attack,
            defense = defense,
            specialAttack = specialAttack,
            specialDefense = specialDefense,
            speed = speed
        )

    companion object {
        internal fun Stats.toTable() =
            StatsTable(
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

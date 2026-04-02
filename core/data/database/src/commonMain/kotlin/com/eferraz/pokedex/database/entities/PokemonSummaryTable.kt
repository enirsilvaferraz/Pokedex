package com.eferraz.pokedex.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eferraz.pokedex.entity.BasePokemon
import com.eferraz.pokedex.entity.detail.PokemonDetailed

@Entity(tableName = "pokemon_ref")
internal data class PokemonSummaryTable(

    @PrimaryKey
    @ColumnInfo(name = "pokemon_id")
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String,
) {

    companion object {

        internal fun BasePokemon.toRefTable() =
            PokemonSummaryTable(
                id = id,
                name = name,
            )

        internal fun PokemonDetailed.toRefTable() =
            PokemonSummaryTable(
                id = id,
                name = name,
            )
    }
}

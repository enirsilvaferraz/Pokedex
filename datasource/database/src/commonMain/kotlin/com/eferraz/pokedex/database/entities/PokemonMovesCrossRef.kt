package com.eferraz.pokedex.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.eferraz.pokedex.entity.PokemonCompleteVO

@Entity(
    tableName = "pokemon_moves",
    primaryKeys = ["pokemon_id", "move_id"],
    foreignKeys = [
        ForeignKey(
            entity = PokemonTable::class,
            parentColumns = ["pokemon_id"],
            childColumns = ["pokemon_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = MoveTable::class,
            parentColumns = ["move_id"],
            childColumns = ["move_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
    ]
)
internal data class PokemonMovesCrossRef(

    @ColumnInfo(name = "pokemon_id")
    val pokemonId: Long,

    @ColumnInfo(name = "move_id")
    val moveId: Long,
) {

    companion object {
        internal fun PokemonCompleteVO.toCrossRef() = this.moves.map {
            PokemonMovesCrossRef(
                pokemonId = this@toCrossRef.id,
                moveId = id
            )
        }
    }
}
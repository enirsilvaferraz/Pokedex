package com.eferraz.pokedex.database.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.eferraz.pokedex.database.entities.PokemonTable
import com.eferraz.pokedex.database.entities.TypeTable

internal data class PokemonAndType(

    @Embedded
    val pokemon: PokemonTable,

    @Relation(
        parentColumn = "type1",
        entityColumn = "type_id",
    )
    val type1: TypeTable,

    @Relation(
        parentColumn = "type2",
        entityColumn = "type_id",
    )
    val type2: TypeTable?,
)
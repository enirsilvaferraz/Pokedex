package com.example.pokedex.database.relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.pokedex.database.entities.PokemonTable
import com.example.pokedex.database.entities.PokemonTypeTable
import com.example.pokedex.database.entities.TypeTable

internal data class PokemonWithTypes(

    @Embedded
    val pokemon: PokemonTable,

    @Relation(
        parentColumn = "pokemon_id",
        entityColumn = "type_id",
        associateBy = Junction(PokemonTypeTable::class)
    )
    val types: List<TypeTable>
)
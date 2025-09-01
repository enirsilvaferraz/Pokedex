package com.eferraz.pokedex.database.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.eferraz.pokedex.database.entities.PokemonTable
import com.eferraz.pokedex.database.entities.TypeTable
import com.eferraz.pokedex.database.entities.TypeTable.Companion.toModel
import com.eferraz.pokedex.entity.PokemonLightVO

internal data class PokemonLight(

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
) {

    fun toModel() = PokemonLightVO(
        id = pokemon.id,
        name = pokemon.name,
        image = pokemon.image,
        type1 = type1.toModel(),
        type2 = type2?.toModel()
    )
}
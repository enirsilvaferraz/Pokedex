package com.eferraz.pokedex.database.relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.eferraz.pokedex.database.entities.AboutTable
import com.eferraz.pokedex.database.entities.BreedingTable
import com.eferraz.pokedex.database.entities.MoveTable
import com.eferraz.pokedex.database.entities.PokemonMovesCrossRef
import com.eferraz.pokedex.database.entities.PokemonTable
import com.eferraz.pokedex.database.entities.StatsTable
import com.eferraz.pokedex.database.entities.TypeTable
import com.eferraz.pokedex.database.entities.TypeTable.Companion.toModel
import com.eferraz.pokedex.entity.PokemonCompleteVO
import com.eferraz.pokedex.entity.PokemonVO

internal data class PokemonComplete(

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

    @Relation(
        entity = AboutTable::class,
        parentColumn = "pokemon_id",
        entityColumn = "about_id"
    )
    val about: AboutWithAbilities,

    @Relation(
        entity = BreedingTable::class,
        parentColumn = "pokemon_id",
        entityColumn = "breeding_id",
    )
    val breeding: BreedingWithEggGroups,

    @Relation(
        parentColumn = "pokemon_id",
        entityColumn = "stats_id",
    )
    val stats: StatsTable,

    @Relation(
        parentColumn = "pokemon_id",
        entityColumn = "move_id",
        associateBy = Junction(value = PokemonMovesCrossRef::class)
    )
    val moves: List<MoveTable>,
) {

    fun toModel(): PokemonCompleteVO = PokemonCompleteVO(
        id = pokemon.id,
        name = pokemon.name,
        image = pokemon.image,
        type1 = type1.toModel(),
        type2 = type2?.toModel(),
        about = about.toModel(),
        breeding = breeding.toModel(),
        stats = stats.toModel(),
        moves = moves.map { it.toModel() }
    )
}
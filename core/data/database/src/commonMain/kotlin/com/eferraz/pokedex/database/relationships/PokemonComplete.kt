package com.eferraz.pokedex.database.relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.eferraz.pokedex.database.entities.AbilityTable.Companion.toTable
import com.eferraz.pokedex.database.entities.AboutTable
import com.eferraz.pokedex.database.entities.AboutTable.Companion.toTable
import com.eferraz.pokedex.database.entities.BreedingTable
import com.eferraz.pokedex.database.entities.BreedingTable.Companion.toTable
import com.eferraz.pokedex.database.entities.EggGroupTable.Companion.toTable
import com.eferraz.pokedex.database.entities.MoveTable
import com.eferraz.pokedex.database.entities.MoveTable.Companion.toTable
import com.eferraz.pokedex.database.entities.PokemonDetailTable
import com.eferraz.pokedex.database.entities.PokemonDetailTable.Companion.toDetailTable
import com.eferraz.pokedex.database.entities.PokemonMovesCrossRef
import com.eferraz.pokedex.database.entities.PokemonSummaryTable
import com.eferraz.pokedex.database.entities.PokemonSummaryTable.Companion.toRefTable
import com.eferraz.pokedex.database.entities.StatsTable
import com.eferraz.pokedex.database.entities.StatsTable.Companion.toTable
import com.eferraz.pokedex.database.entities.TypeTable
import com.eferraz.pokedex.database.entities.TypeTable.Companion.toModel
import com.eferraz.pokedex.database.entities.TypeTable.Companion.toTable
import com.eferraz.pokedex.entity.detail.PokemonDetailed as PokemonDetailedModel

internal data class PokemonComplete(

    @Embedded
    val detail: PokemonDetailTable,

    @Relation(
        parentColumn = "pokemon_id",
        entityColumn = "pokemon_id",
        entity = PokemonSummaryTable::class
    )
    val ref: List<PokemonSummaryTable>,

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

    companion object {
        fun PokemonDetailedModel.toTable2() = PokemonComplete(
            detail = toDetailTable(),
            ref = listOf(toRefTable()),
            type1 = type1.toTable(),
            type2 = type2?.toTable(),
            about = AboutWithAbilities(about.toTable(), about.abilities.map { it.toTable() }),
            breeding = BreedingWithEggGroups(breeding.toTable(), breeding.eggGroups.map { it.toTable() }),
            stats = stats.toTable(),
            moves = moves.map { it.toTable() }
        )
    }

    fun toModel(): PokemonDetailedModel = PokemonDetailedModel(
        id = detail.pokemonId,
        name = ref.single().name,
        image = detail.image,
        type1 = type1.toModel(),
        type2 = type2?.toModel(),
        about = about.toModel(),
        breeding = breeding.toModel(),
        stats = stats.toModel(),
        moves = moves.map { it.toModel() }
    )
}

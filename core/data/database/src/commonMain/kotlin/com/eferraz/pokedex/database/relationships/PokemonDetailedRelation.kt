package com.eferraz.pokedex.database.relationships

import androidx.room3.ColumnInfo
import androidx.room3.Junction
import androidx.room3.Relation
import com.eferraz.pokedex.database.entities.AbilityTable
import com.eferraz.pokedex.database.entities.AboutAbilitiesCrossRef
import com.eferraz.pokedex.database.entities.AboutTable
import com.eferraz.pokedex.database.entities.BreedingEggGroupCrossRef
import com.eferraz.pokedex.database.entities.BreedingTable
import com.eferraz.pokedex.database.entities.EggGroupTable
import com.eferraz.pokedex.database.entities.MoveTable
import com.eferraz.pokedex.database.entities.PokemonMovesCrossRef
import com.eferraz.pokedex.database.entities.SpeciesTable
import com.eferraz.pokedex.database.entities.StatsTable
import com.eferraz.pokedex.database.entities.TypeTable
import com.eferraz.pokedex.database.entities.TypeTable.Companion.toModel
import com.eferraz.pokedex.entity.detail.PokemonDetailed as PokemonDetailedModel

/**
 * Agregado de leitura Room: uma linha do JOIN `pokemon_ref` + `pokemon_detail`
 * com FKs como colunas e grafos via `@Relation`.
 */
internal data class PokemonDetailedRelation(

    @ColumnInfo(name = "pokemon_id")
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "type1")
    val typeId1: Long,

    @ColumnInfo(name = "type2")
    val typeId2: Long?,

    @ColumnInfo(name = "species")
    val speciesId: Long?,

    @ColumnInfo(name = "about")
    val aboutId: Long,

    @ColumnInfo(name = "stats")
    val statsId: Long,

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
        entity = SpeciesTable::class,
        parentColumn = "species",
        entityColumn = "species_id",
    )
    val species: SpeciesTable?,

    @Relation(
        entity = AboutTable::class,
        parentColumn = "about",
        entityColumn = "about_id",
    )
    val aboutRow: AboutTable,

    @Relation(
        entity = AbilityTable::class,
        parentColumn = "about",
        entityColumn = "ability_id",
        associateBy = Junction(
            value = AboutAbilitiesCrossRef::class,
            parentColumn = "about_id",
            entityColumn = "ability_id",
        ),
    )
    val abilities: List<AbilityTable>,

    @Relation(
        entity = BreedingTable::class,
        parentColumn = "species",
        entityColumn = "species_id",
    )
    val breedingRow: BreedingTable?,

    @Relation(
        entity = EggGroupTable::class,
        parentColumn = "species",
        entityColumn = "egg_group_id",
        associateBy = Junction(
            value = BreedingEggGroupCrossRef::class,
            parentColumn = "species_id",
            entityColumn = "egg_group_id",
        ),
    )
    val eggGroups: List<EggGroupTable>,

    @Relation(
        parentColumn = "stats",
        entityColumn = "stats_id",
    )
    val stats: StatsTable,

    @Relation(
        parentColumn = "pokemon_id",
        entityColumn = "move_id",
        associateBy = Junction(value = PokemonMovesCrossRef::class),
    )
    val moves: List<MoveTable>,
) {

    fun toModel(): PokemonDetailedModel =
        PokemonDetailedModel(
            id = id,
            name = name,
            image = image,
            type1 = type1.toModel(),
            type2 = type2?.toModel(),
            species = species?.let { st ->
                breedingRow?.let { br -> st.toModel(br.toModel(eggGroups)) }
            },
            about = aboutRow.toModel(abilities),
            stats = stats.toModel(),
            moves = moves.map { it.toModel() },
        )
}

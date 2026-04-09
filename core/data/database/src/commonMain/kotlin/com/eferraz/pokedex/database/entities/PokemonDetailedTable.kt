package com.eferraz.pokedex.database.entities

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey
import com.eferraz.pokedex.entity.detail.PokemonDetailed

@Entity(
    tableName = "pokemon_detail",
    foreignKeys = [
        ForeignKey(
            entity = PokemonSummaryTable::class,
            parentColumns = ["pokemon_id"],
            childColumns = ["pokemon_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = TypeTable::class,
            parentColumns = ["type_id"],
            childColumns = ["type1"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = TypeTable::class,
            parentColumns = ["type_id"],
            childColumns = ["type2"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = SpeciesTable::class,
            parentColumns = ["species_id"],
            childColumns = ["species"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = AboutTable::class,
            parentColumns = ["about_id"],
            childColumns = ["about"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = StatsTable::class,
            parentColumns = ["stats_id"],
            childColumns = ["stats"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index(value = ["type1"]),
        Index(value = ["type2"]),
        Index(value = ["species"]),
        Index(value = ["about"]),
        Index(value = ["stats"]),
    ],
)
internal data class PokemonDetailedTable(

    @PrimaryKey
    @ColumnInfo(name = "pokemon_id")
    val pokemonId: Long,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "type1")
    val typeID1: Long,

    @ColumnInfo(name = "type2")
    val typeID2: Long? = null,

    @ColumnInfo(name = "species")
    val speciesID: Long? = null,

    @ColumnInfo(name = "about")
    val aboutID: Long,

    @ColumnInfo(name = "stats")
    val statsID: Long,
) {

    companion object {

        internal fun PokemonDetailed.toDetailTable() =
            PokemonDetailedTable(
                pokemonId = id,
                image = image,
                typeID1 = type1.id,
                typeID2 = type2?.id,
                speciesID = species?.id,
                aboutID = about.id,
                statsID = stats.id,
            )
    }
}

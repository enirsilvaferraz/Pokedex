package com.eferraz.pokedex.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.eferraz.pokedex.entity.PokemonCompleteVO

@Entity(
    tableName = "pokemon",
    foreignKeys = [
        ForeignKey(
            entity = TypeTable::class,
            parentColumns = ["type_id"],
            childColumns = ["type1"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = TypeTable::class,
            parentColumns = ["type_id"],
            childColumns = ["type2"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AboutTable::class,
            parentColumns = ["about_id"],
            childColumns = ["about"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = BreedingTable::class,
            parentColumns = ["breeding_id"],
            childColumns = ["breeding"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = StatsTable::class,
            parentColumns = ["stats_id"],
            childColumns = ["stats"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
    ]
)
internal data class PokemonTable(

    @PrimaryKey
    @ColumnInfo(name = "pokemon_id")
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "type1")
    val typeID1: Long,

    @ColumnInfo(name = "type2")
    val typeID2: Long? = null,

    @ColumnInfo(name = "about")
    val aboutID: Long,

    @ColumnInfo(name = "breeding")
    val breedingID: Long,

    @ColumnInfo(name = "stats")
    val statsID: Long,
) {

    companion object {
        internal fun PokemonCompleteVO.toTable() = PokemonTable(
            id = id,
            name = name,
            image = image,
            typeID1 = type1.id,
            typeID2 = type2?.id,
            aboutID = about.id,
            breedingID = breeding.id,
            statsID = stats.id,
        )
    }
}

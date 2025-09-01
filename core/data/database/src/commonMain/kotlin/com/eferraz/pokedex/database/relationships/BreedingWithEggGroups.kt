package com.eferraz.pokedex.database.relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.eferraz.pokedex.database.entities.BreedingEggGroupCrossRef
import com.eferraz.pokedex.database.entities.BreedingTable
import com.eferraz.pokedex.database.entities.EggGroupTable
import com.eferraz.pokedex.entity.BreedingVO

internal data class BreedingWithEggGroups(

    @Embedded
    val breeding: BreedingTable,

    @Relation(
        parentColumn = "breeding_id",
        entityColumn = "egg_group_id",
        associateBy = Junction(value = BreedingEggGroupCrossRef::class)
    )
    val eggGroups: List<EggGroupTable>
) {
    fun toModel() = BreedingVO(
        id = breeding.id,
//        hatchCounter = breeding.hatchCounter,
        genderRatio = breeding.genderRatio,
        eggGroups = eggGroups.map { it.toModel() }
    )
}
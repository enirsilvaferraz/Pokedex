package com.eferraz.pokedex.database.relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.eferraz.pokedex.database.entities.AbilityTable
import com.eferraz.pokedex.database.entities.AboutAbilitiesCrossRef
import com.eferraz.pokedex.database.entities.AboutTable
import com.eferraz.pokedex.entity.AboutVO

internal data class AboutWithAbilities(

    @Embedded
    val about: AboutTable,

    @Relation(
        entity = AbilityTable::class,
        parentColumn = "about_id",
        entityColumn = "ability_id",
        associateBy = Junction(
            value = AboutAbilitiesCrossRef::class,
            parentColumn = "about_id",
            entityColumn = "ability_id",
        )
    )
    val abilities: List<AbilityTable>,
) {

    fun toModel() = AboutVO(
        id = about.id,
        description = about.description,
        species = about.species,
        category = about.category,
        height = about.height,
        weight = about.weight,
        abilities = abilities.map { it.toModel() }
    )
}
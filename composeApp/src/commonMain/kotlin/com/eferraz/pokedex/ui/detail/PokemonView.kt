package com.eferraz.pokedex.ui.detail

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.eferraz.pokedex.entity.PokemonVO
import com.eferraz.pokedex.helpers.getColorForType

internal data class PokemonView(
    val id: String,
    val name: String,
    val types: List<String>,
    val image: String,
    val description: String,
    val color: Color,
    val about: About,
    val breeding: Breeding,
    val stats: Stats,
) {

    constructor(model: PokemonVO) : this(
        id = "#" + model.id.toString().padStart(3, '0'),
        name = model.name.capitalize(Locale.current),
        types = model.types().map { it.name.capitalize(Locale.current) },
        image = model.image,
        description = model.description,
        color = model.type1.getColorForType(),
        about = About(model),
        breeding = Breeding(model),
        stats = Stats(model)
    )

    data class About(
        val species: String,
        val category: String,
        val height: String,
        val weight: String,
        val abilities: String,
    ) {

        constructor(model: PokemonVO) : this(
            species = model.genera.capitalize(Locale.current),
            category = model.category.capitalize(Locale.current),
            height = "0.70 cm",
            weight = "6.9 kg",
            abilities = listOf("Chlorophyll").joinToString(", ") { it.capitalize(Locale.current) }
        )
    }

    data class Breeding(
        val gender: String,
        val eggGroups: String,
        val eggCycle: String,
    ) {

        constructor(model: PokemonVO) : this(
            gender = "87,5% Male, 12,5% Female",
            eggGroups = "Monster",
            eggCycle = "Grass"
        )

        companion object {
            fun formatGender(male: Double) = "87,5% Male, 12,5% Female"
        }
    }

    data class Stats(
        val hp: Item,
        val attack: Item,
        val defense: Item,
        val spAtk: Item,
        val spDef: Item,
        val speed: Item,
    ) {

        constructor(model: PokemonVO) : this(
            hp = Item(45),
            attack = Item(49),
            defense = Item(49),
            spAtk = Item(65),
            spDef = Item(65),
            speed = Item(45)
        )

        val total = Item(listOf(hp, attack, defense, spAtk, spDef, speed).sumOf { it.value }, max = 600)

        data class Item(
            val value: Int,
            val max: Int = 100,
        ) {

            fun text() = value.toString()

            fun progress() = value.toFloat() / max.toFloat()

            fun color() = if (value > max / 2) Color.Green else Color.Red
        }
    }
}
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
    val abilities: List<Ability>,
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
        stats = Stats(model),
        abilities = model.abilities.map { Ability(it.id, it.name) }
    )

    internal fun aboutItems() = mapOf(
        "About" to about.items(),
        "Breeding" to breeding.items()
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

        fun items() = mapOf(
            "Species" to species,
            "Category" to category,
            "Height" to height,
            "Weight" to weight,
            "Abilities" to abilities
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

        fun items() = mapOf(
            "Gender" to gender,
            "Egg Groups" to eggGroups,
            "Egg Cycle" to eggCycle
        )
    }

    data class Stats(
        private val hp: Item,
        private val attack: Item,
        private val defense: Item,
        private val spAtk: Item,
        private val spDef: Item,
        private val speed: Item,
    ) {

        constructor(model: PokemonVO) : this(
            hp = Item(45),
            attack = Item(49),
            defense = Item(49),
            spAtk = Item(65),
            spDef = Item(65),
            speed = Item(45)
        )

        private val total = Item(listOf(hp, attack, defense, spAtk, spDef, speed).sumOf { it.value }, max = 600)

        fun items() = mapOf(
            "HP" to hp,
            "Attack" to attack,
            "Defense" to defense,
            "Sp. Atk" to spAtk,
            "Sp. Def" to spDef,
            "Speed" to speed,
            "Total" to total
        )

        data class Item(
            val value: Int,
            private val max: Int = 100,
        ) {

            fun text() = value.toString()

            fun progress() = value.toFloat() / max.toFloat()

            fun color() = if (value > max / 2) Color.Green else Color.Red
        }
    }

    data class Ability(
        val id: Long,
        val name: String,
    )
}
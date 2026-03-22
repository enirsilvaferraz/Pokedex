package com.eferraz.pokedex.repositories

import com.eferraz.pokedex.entity.detail.About
import com.eferraz.pokedex.entity.detail.Breeding
import com.eferraz.pokedex.entity.detail.PokemonDetailed
import com.eferraz.pokedex.entity.detail.Species
import com.eferraz.pokedex.entity.detail.Stats
import com.eferraz.pokedex.entity.detail.Type
import com.eferraz.pokedex.entity.summary.PokemonSummary

fun sampleType(id: Long = 1L, name: String = "grass"): Type =
    Type(id = id, name = name)

fun sampleSummary(id: Long = 1L, name: String = "bulbasaur"): PokemonSummary =
    PokemonSummary(id = id, name = name, type1 = sampleType())

fun sampleSpecies(id: Long = 1L): Species =
    Species(
        id = id,
        description = "A seed Pokémon.",
        species = "Seed",
        category = "Monster",
        breeding = Breeding(id = id, genderRatio = 4f, eggGroups = emptyList()),
    )

fun sampleDetailed(id: Long = 1L): PokemonDetailed =
    PokemonDetailed(
        id = id,
        name = "bulbasaur",
        image = "https://example.com/$id.png",
        type1 = sampleType(),
        type2 = null,
        species = null,
        about = About(id = id, height = 0.7f, weight = 6.9f, abilities = emptyList()),
        stats = Stats(
            id = id,
            hp = 45,
            attack = 49,
            defense = 49,
            specialAttack = 65,
            specialDefense = 65,
            speed = 45,
        ),
        moves = emptyList(),
    )

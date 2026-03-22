package com.eferraz.pokedex.usecases

import com.eferraz.pokedex.entity.detail.About
import com.eferraz.pokedex.entity.detail.Breeding
import com.eferraz.pokedex.entity.detail.PokemonDetailed
import com.eferraz.pokedex.entity.detail.Species
import com.eferraz.pokedex.entity.detail.Stats
import com.eferraz.pokedex.entity.detail.Type
import com.eferraz.pokedex.entity.summary.PokemonSummary

internal fun testType(id: Long = 1L, name: String = "grass"): Type = Type(id = id, name = name)

internal fun testSpecies(id: Long = 1L): Species = Species(
    id = id,
    description = "desc",
    species = "seed",
    category = "monster",
    breeding = Breeding(id = id, genderRatio = 4f, eggGroups = emptyList()),
)

internal fun testPokemonDetailed(
    id: Long = 1L,
    name: String = "bulbasaur",
    species: Species? = null,
): PokemonDetailed = PokemonDetailed(
    id = id,
    name = name,
    image = "https://example.com/$id.png",
    type1 = testType(),
    type2 = null,
    species = species,
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

internal fun testPokemonSummary(
    id: Long = 1L,
    name: String = "pikachu",
): PokemonSummary = PokemonSummary(id = id, name = name, type1 = testType())

package com.eferraz.pokedex.database.helpers

import com.eferraz.pokedex.database.entities.PokemonTable
import com.eferraz.pokedex.database.entities.TypeTable
import com.eferraz.pokedex.database.relationships.PokemonAndType
import com.eferraz.pokedex.entity.PokemonVO
import com.eferraz.pokedex.entity.TypeVO

internal fun TypeTable.toVO() = TypeVO(
    id = id,
    name = name
)

internal fun PokemonAndType.toVO() = PokemonVO(
    id = pokemon.id,
    name = pokemon.name,
    image = pokemon.image,
    type1 = type1.toVO(),
    type2 = type2?.toVO(),
    description = pokemon.description,
    weight = pokemon.weight,
    height = pokemon.height,
    category = pokemon.category,
//    abilities = pokemon.abilities,
    genderRatioMale = pokemon.genderRatio,
//    baseStats = pokemon.stats.map { it.toVO() },
    primaryColor = pokemon.primaryColor,
    genera = pokemon.genera
)

internal fun PokemonVO.toTable() = PokemonTable(
    id = id,
    name = name,
    image = image,
    typeID1 = type1.id,
    typeID2 = type2?.id,
    description = description,
    weight = weight,
    height = height,
    category = category,
//    abilities = abilities,
    genderRatio = genderRatioMale,
//    stats = baseStats.map { it.toTable() },
    primaryColor = primaryColor,
    genera = genera
)

internal fun TypeVO.toTable() = TypeTable(
    id = id,
    name = name
)

package com.example.pokedex.network.helpers

import com.example.pokedex.entity.PokemonVO
import com.example.pokedex.entity.StatVO
import com.example.pokedex.entity.TypeVO
import com.example.pokedex.network.responses.Pokemon
import com.example.pokedex.network.responses.PokemonSpeciesDetail
import com.example.pokedex.network.responses.StatEntry
import com.example.pokedex.network.responses.TypeEntry

internal fun toVO(pokemon: Pokemon, species: PokemonSpeciesDetail): PokemonVO = PokemonVO(
    id = pokemon.id,
    name = pokemon.name,
    image = pokemon.sprites.frontDefault,
    type1 = pokemon.types.first { it.slot == 1 }.toVO(),
    type2 = pokemon.types.firstOrNull { it.slot == 2 }?.toVO(),
    weight = pokemon.weight.toFloat(),
    height = pokemon.height.toFloat(),
    description = species.flavorTextEntries.first { it.language.name == "en" }.flavorText,
    category = species.shape?.name.orEmpty(),
//    abilities = pokemon.abilities.map { it.ability.name },
    genderRatioMale = species.genderRate.toFloat(),
//    baseStats = pokemon.stats.map { toVO(it) },
    primaryColor = species.color.name.orEmpty(),
)

private fun TypeEntry.toVO(): TypeVO = TypeVO(
    id = type.url.removeSuffix("/").let { it.substring(it.lastIndexOf("/") + 1) }.toLong(),
    name = type.name.orEmpty(),
)

private fun toVO(stat: StatEntry): StatVO = StatVO(
    name = stat.stat.name.orEmpty(),
    value = stat.baseStat,
)

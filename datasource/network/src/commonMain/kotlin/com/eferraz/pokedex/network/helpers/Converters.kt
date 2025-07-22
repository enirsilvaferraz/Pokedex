package com.eferraz.pokedex.network.helpers

import com.eferraz.pokedex.entity.Ability
import com.eferraz.pokedex.entity.PokemonVO
import com.eferraz.pokedex.entity.StatVO
import com.eferraz.pokedex.entity.TypeVO
import com.eferraz.pokedex.network.responses.Pokemon
import com.eferraz.pokedex.network.responses.PokemonSpeciesDetail
import com.eferraz.pokedex.network.responses.StatEntry
import com.eferraz.pokedex.network.responses.TypeEntry

internal fun toVO(pokemon: Pokemon, species: PokemonSpeciesDetail): PokemonVO = PokemonVO(
    id = pokemon.id,
    name = pokemon.name,
    image = pokemon.sprites.other?.officialArtwork?.frontDefault ?: pokemon.sprites.frontDefault,
    type1 = pokemon.types.first { it.slot == 1 }.toVO(),
    type2 = pokemon.types.firstOrNull { it.slot == 2 }?.toVO(),
    weight = pokemon.weight.toFloat(),
    height = pokemon.height.toFloat(),
    description = species.flavorTextEntries.first { it.language.name == "en" }.flavorText.replace("\n", " ").replace(Regex("\\f"), " "),
    category = species.shape?.name.orEmpty(),
    abilities = pokemon.abilities.map { Ability(it.ability.getId(), it.ability.name.orEmpty()) },
    genderRatioMale = species.genderRate.toFloat(),
//    baseStats = pokemon.stats.map { toVO(it) },
    primaryColor = species.color.name.orEmpty(),
    genera = species.genera.first { it.language.name == "en" }.genus,
)

private fun TypeEntry.toVO(): TypeVO = TypeVO(
    id = type.url.removeSuffix("/").let { it.substring(it.lastIndexOf("/") + 1) }.toLong(),
    name = type.name.orEmpty(),
)

private fun toVO(stat: StatEntry): StatVO = StatVO(
    name = stat.stat.name.orEmpty(),
    value = stat.baseStat,
)
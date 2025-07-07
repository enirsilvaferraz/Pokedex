package com.example.pokedex.network.helpers

import com.example.pokedex.entity.PokemonVO
import com.example.pokedex.entity.TypeVO
import com.example.pokedex.network.responses.Pokemon
import com.example.pokedex.network.responses.TypeEntry

internal fun Pokemon.toVO(): PokemonVO = PokemonVO(
    id = id,
    name = name,
    image = sprites.frontDefault,
    type1 = types.find { it.slot == 1 }?.toVO(),
    type2 = types.find { it.slot == 2 }?.toVO(),
//    weightInKg = weight.toFloat(),
//    heightInMeters = height.toFloat(),
)

private fun TypeEntry.toVO(): TypeVO = TypeVO(
    id = type.url.removeSuffix("/").let { it.substring(it.lastIndexOf("/") + 1) }.toLong(),
    name = type.name,
)
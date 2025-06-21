package com.example.pokedex.extensions

import com.example.pokedex.entity.PokemonVO
import com.example.pokedex.formatId
import com.example.pokedex.ui.theme.getColorForType

fun PokemonVO.formatedId() = formatId(id)

fun PokemonVO.color() = types.first().getColorForType()
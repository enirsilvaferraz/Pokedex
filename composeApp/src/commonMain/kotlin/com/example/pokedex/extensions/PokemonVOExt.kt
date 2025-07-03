package com.example.pokedex.extensions

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.example.pokedex.entity.PokemonVO
import com.example.pokedex.formatId

fun PokemonVO.formatedId() = formatId(id)

fun PokemonVO.color() = type1.name.getColorForType()

fun PokemonVO.formatedName() = name.capitalize(Locale.current)
package com.example.pokedex.helpers

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.example.pokedex.entity.PokemonVO
import com.example.pokedex.formatId

fun PokemonVO.formatedId() = formatId(id)

fun PokemonVO?.color() = this?.type1.getColorForType()

fun PokemonVO.formatedName() = name.capitalize(Locale.current)
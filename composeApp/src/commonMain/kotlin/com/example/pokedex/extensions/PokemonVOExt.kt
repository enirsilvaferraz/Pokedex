package com.example.pokedex.extensions

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.example.pokedex.entity.PokemonVO
import com.example.pokedex.formatId
import com.example.pokedex.ui.theme.getColorForType

fun PokemonVO.formatedId() = formatId(id)

fun PokemonVO.color() = types.first().getColorForType()

fun PokemonVO.formatedName() = name.capitalize(Locale.current)
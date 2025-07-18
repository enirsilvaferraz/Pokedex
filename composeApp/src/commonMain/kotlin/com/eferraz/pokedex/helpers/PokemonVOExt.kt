package com.eferraz.pokedex.helpers

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.eferraz.pokedex.entity.PokemonVO
import com.eferraz.pokedex.formatId

internal fun PokemonVO.formatedId() = formatId(id)

internal fun PokemonVO?.color() = this?.type1.getColorForType()

internal fun PokemonVO.formatedName() = name.capitalize(Locale.current)
package com.eferraz.pokedex.helpers

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.eferraz.pokedex.entity.PokemonVO
import com.eferraz.pokedex.formatId

@Deprecated("Não usar")
internal fun PokemonVO.formatedId() = formatId(id)

@Deprecated("Não usar")
internal fun PokemonVO.formatedName() = name.capitalize(Locale.current)
package com.eferraz.pokedex.helpers

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.eferraz.pokedex.entity.BasePokemon
import com.eferraz.pokedex.formatId

@Deprecated("Não usar")
internal fun BasePokemon.formatedId() = formatId(id)

@Deprecated("Não usar")
internal fun BasePokemon.formatedName() = name.capitalize(Locale.current)
package com.eferraz.pokedex.helpers

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale

internal fun String.capitalizeName() = this.capitalize(Locale.current)

internal fun Float.formatPercent() = (this * 100).formatDecimal() + "%"

internal fun Float.formatHeight() = (this * 10).formatDecimal() + " cm"

internal fun Float.formatWeight() = (this / 10).formatDecimal() + " kg"

internal fun Long.formatID() = "#" + this.toString().padStart(3, '0')

internal fun List<String>.formatList() = this.joinToString(", ") { it.capitalizeName() }

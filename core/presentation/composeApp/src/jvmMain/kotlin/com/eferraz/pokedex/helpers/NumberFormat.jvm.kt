package com.eferraz.pokedex.helpers

import androidx.compose.ui.text.intl.Locale
import java.text.NumberFormat

internal actual fun Float.formatDecimal(): String =
    NumberFormat.getNumberInstance(Locale.current.platformLocale).format(this)
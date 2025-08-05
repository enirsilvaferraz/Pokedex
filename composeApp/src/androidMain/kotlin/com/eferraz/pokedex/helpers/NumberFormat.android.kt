package com.eferraz.pokedex.helpers

import androidx.compose.ui.text.intl.Locale
import org.koin.core.annotation.Single
import java.text.NumberFormat

@Single
internal actual fun Float.formatDecimal(): String =
    NumberFormat.getNumberInstance(Locale.current.platformLocale).format(this)
package com.eferraz.pokedex.helpers

import androidx.compose.ui.text.intl.PlatformLocale
import org.koin.core.annotation.Single
import java.text.NumberFormat

@Single
internal actual fun Float.format(locale: PlatformLocale): String =
    NumberFormat.getNumberInstance(locale).format(this)
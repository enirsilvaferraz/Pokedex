package com.eferraz.pokedex.helpers

import androidx.compose.ui.text.intl.Locale
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle


internal actual fun Float.formatDecimal() =
    NSNumberFormatter().apply {
        this@apply.numberStyle = NSNumberFormatterDecimalStyle
        this@apply.locale = Locale.current.platformLocale
    }.stringFromNumber(NSNumber(this)) ?: throw IllegalStateException("Invalid number")

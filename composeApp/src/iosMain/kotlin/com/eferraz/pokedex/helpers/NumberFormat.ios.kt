package com.eferraz.pokedex.helpers

import androidx.compose.ui.text.intl.PlatformLocale
import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle


internal actual fun Float.format(locale: PlatformLocale) =
    NSNumberFormatter().apply {
        this@NSNumberFormatter.numberStyle = NSNumberFormatterDecimalStyle
        this@NSNumberFormatter.locale = locale
    }.stringFromNumber(NSNumber(this)) ?: throw IllegalStateException("Invalid number")

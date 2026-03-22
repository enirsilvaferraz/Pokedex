package com.eferraz.pokedex.components.templates

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.LayoutDirection

@Composable
internal fun PokedexTheme(content: @Composable () -> Unit) {

    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy( // todo darkmode
            background = Color.White,
            surface = Color.White
        ),
        content = content
    )
}
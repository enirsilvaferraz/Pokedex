package com.eferraz.pokedex.components.templates

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

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

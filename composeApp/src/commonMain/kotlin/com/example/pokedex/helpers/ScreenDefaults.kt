package com.example.pokedex.helpers

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AppScaffold(
    modifier: Modifier = Modifier,
    title: String? = null,
    content: @Composable (Modifier) -> Unit,
) {

    PokedexTheme {

        Scaffold(
            modifier = modifier,
            topBar = {
                title?.let {
                    TopAppBar(
                        title = {
                            Text(
                                title,
                                style = MaterialTheme.typography.headlineLarge,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    )
                }
            }
        ) { innerPadding ->
            content(Modifier.edgeToEdgePadding(innerPadding, LocalLayoutDirection.current))
        }
    }
}

internal fun Modifier.edgeToEdgePadding(innerPadding: PaddingValues, current: LayoutDirection): Modifier = padding(
    top = innerPadding.calculateTopPadding(),
    start = innerPadding.calculateStartPadding(current),
    end = innerPadding.calculateEndPadding(current),
)


@Composable
internal fun PokedexTheme(content: @Composable () -> Unit) {

    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            background = Color.White,
            surface = Color.White
        ),
        content = content
    )
}
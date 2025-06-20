package com.example.pokedex

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AppScaffold(
    title: String,
    content: @Composable (Modifier) -> Unit
) {

    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            background = Color.White,
            surface = Color.White
        )
    ) {

        Scaffold(
            topBar = {
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
        ) { innerPadding ->
            val modifier = Modifier.padding(
                top = innerPadding.calculateTopPadding(),
                start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
            )
            content(modifier)
        }
    }
}

@Composable
internal fun CollectionScreen(
    modifier: Modifier,
    list: List<PokemonVO>,
    onItem: @Composable (PokemonVO) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(24.dp)
    ) {
        items(items = list, key = { it.id }) {
            onItem(it)
        }
    }
}

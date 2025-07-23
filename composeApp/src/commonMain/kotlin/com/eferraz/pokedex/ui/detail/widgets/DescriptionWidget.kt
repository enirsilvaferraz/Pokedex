package com.eferraz.pokedex.ui.detail.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eferraz.pokedex.helpers.PokedexTheme
import com.eferraz.pokedex.ui.detail.components.CardComponent
import com.eferraz.pokedex.ui.preview.pokemon
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun ParagraphWidget(
    modifier: Modifier = Modifier,
    description: String,
) {

    CardComponent(modifier = modifier) {
        Text(text = description, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview
@Composable
private fun ParagraphWidgetPreview(
//    @PreviewParameter(PokemonParamProvider::class) pokemon: PokemonView
) {
    PokedexTheme {
        Surface(color = pokemon.background.color) {
            ParagraphWidget(modifier = Modifier.padding(24.dp), description = pokemon.description)
        }
    }
}
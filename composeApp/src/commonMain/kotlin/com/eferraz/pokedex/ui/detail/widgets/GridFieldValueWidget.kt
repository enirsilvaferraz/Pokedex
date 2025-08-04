package com.eferraz.pokedex.ui.detail.widgets

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eferraz.pokedex.helpers.PokedexTheme
import com.eferraz.pokedex.ui.detail.components.CardComponent
import com.eferraz.pokedex.ui.detail.components.CardTitleComponent
import com.eferraz.pokedex.ui.preview.pokemon
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun GridFieldValueWidget(
    modifier: Modifier = Modifier,
    title: String,
    items: Map<String, String>,
) {

    CardComponent(modifier = modifier) {

        CardTitleComponent(title = title)

        FlowRow(
            maxItemsInEachRow = 2,
            horizontalArrangement = spacedBy(8.dp),
            verticalArrangement = spacedBy(8.dp),
        ) {

            items.forEach { (key, value) ->

                Row(modifier = Modifier.weight(0.5f)) {

                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        text = key,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Text(
                        modifier = Modifier.weight(1f),
                        text = value,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun GridFieldValueWidgetPreview(
//    @PreviewParameter(PokemonParamProvider::class) pokemon: PokemonView
) {
    PokedexTheme {
        Surface(color = pokemon.background.color) {
            GridFieldValueWidget(modifier = Modifier.padding(24.dp), title = "Moves", items = pokemon.moves.items)
        }
    }
}

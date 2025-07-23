package com.eferraz.pokedex.ui.detail.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.eferraz.pokedex.helpers.PokedexTheme
import com.eferraz.pokedex.ui.detail.components.CardComponent
import com.eferraz.pokedex.ui.detail.components.CardTitleComponent
import com.eferraz.pokedex.ui.detail.vos.FieldValueVo
import com.eferraz.pokedex.ui.preview.pokemon
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun FieldValueWidget(
    modifier: Modifier = Modifier,
    title: String,
    items: List<FieldValueVo>,
) {

    CardComponent(modifier = modifier) {

        CardTitleComponent(title = title)

        items.forEach { (field, value) ->

            Row(verticalAlignment = CenterVertically) {

                Text(
                    modifier = Modifier.width(110.dp),
                    text = field,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    modifier = Modifier.weight(1f),
                    text = value,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
private fun FieldValueWidgetPreview(
//    @PreviewParameter(PokemonParamProvider::class) pokemon: PokemonView
) {
    PokedexTheme {
        Surface(color = pokemon.background.color) {
            FieldValueWidget(modifier = Modifier.padding(24.dp), title = "About", items = pokemon.about.items)
        }
    }
}
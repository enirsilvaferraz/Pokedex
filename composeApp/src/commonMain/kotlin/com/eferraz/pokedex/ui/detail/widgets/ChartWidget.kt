package com.eferraz.pokedex.ui.detail.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.eferraz.pokedex.helpers.PokedexTheme
import com.eferraz.pokedex.ui.detail.components.CardComponent
import com.eferraz.pokedex.ui.detail.components.CardTitleComponent
import com.eferraz.pokedex.ui.detail.vos.PokemonDetailVo
import com.eferraz.pokedex.ui.preview.pokemon
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun ChartWidget(
    modifier: Modifier = Modifier,
    title: String,
    items: List<PokemonDetailVo.Stats.ChartItem>,
) {
    CardComponent(modifier = modifier) {

        CardTitleComponent(title = title)

        items.forEach { item ->

            Row(verticalAlignment = CenterVertically) {

                Text(
                    modifier = Modifier.width(110.dp),
                    text = item.field,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    modifier = Modifier.width(40.dp),
                    text = item.text(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )

                LinearProgressIndicator(
                    modifier = Modifier.weight(0.7f).height(4.dp),
                    progress = { item.progress() },
                    color = item.color(),
                    trackColor = Color.Gray.copy(alpha = 0.1f)
                )
            }
        }
    }
}


@Preview
@Composable
private fun ChartWidgetPreview(
//    @PreviewParameter(PokemonParamProvider::class) pokemon: PokemonView
) {
    PokedexTheme {
        Surface(color = pokemon.background.color) {
            ChartWidget(modifier = Modifier.padding(24.dp), title = "Stats", items = pokemon.stats.items)
        }
    }
}
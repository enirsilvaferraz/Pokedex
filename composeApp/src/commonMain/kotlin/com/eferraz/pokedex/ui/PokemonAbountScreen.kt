package com.eferraz.pokedex.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
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
import org.jetbrains.compose.ui.tooling.preview.Preview

internal data class ScreenDetail(
    val title: String? = null,
    val fields: List<FieldTypes>,
) {

    sealed interface FieldTypes

    data class FieldValue(val field: String, val value: String) : FieldTypes

    data class Chart(val field: String, val value: Int, val maxValue: Int = 100) : FieldTypes {
        fun progress() = value.toFloat() / maxValue.toFloat()
        fun color() = if (value > maxValue / 2) Color.Green else Color.Red
    }

    data class SortedValue(val number: Int, val text: String) : FieldTypes
}

@Composable
internal fun PokemonAboutScreen(modifier: Modifier = Modifier, screenDetail: List<ScreenDetail>) {

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = spacedBy(24.dp)
    ) {

        screenDetail.forEach { detail ->

            Column(verticalArrangement = spacedBy(6.dp)) {

                detail.title?.let { title ->
                    Text(
                        title,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 4.dp),
                        fontWeight = FontWeight.Bold
                    )
                }

                detail.fields.forEach {

                    Row(verticalAlignment = CenterVertically) {

                        when (it) {
                            is ScreenDetail.FieldValue -> FieldValueComponent(field = it.field, value = it.value)
                            is ScreenDetail.Chart -> ChartComponent(field = it)
                            is ScreenDetail.SortedValue -> SortedValueComponent(field = it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.FieldValueComponent(field: String, value: String) {

    Text(
        modifier = Modifier.width(120.dp),
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

@Composable
private fun RowScope.ChartComponent(field: ScreenDetail.Chart) {

    Text(
        modifier = Modifier.width(120.dp),
        text = field.field,
        style = MaterialTheme.typography.bodyMedium
    )

    Text(
        modifier = Modifier.width(40.dp),
        text = field.value.toString(),
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold
    )

    LinearProgressIndicator(
        modifier = Modifier.weight(0.7f).height(4.dp),
        progress = { field.progress() },
        color = field.color(),
        trackColor = Color.Gray.copy(alpha = 0.1f)
    )
}

@Composable
private fun RowScope.SortedValueComponent(field: ScreenDetail.SortedValue) {

    Text(
        modifier = Modifier.padding(end = 8.dp),
        text = field.number.toString() + ".",
        style = MaterialTheme.typography.bodyMedium
    )

    Text(
        modifier = Modifier.weight(1f),
        text = field.text,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold
    )
}

@Preview
@Composable
private fun PokemonAboutScreenPreview() {
    PokedexTheme {
        Surface {
            PokemonAboutScreen(
                modifier = Modifier.padding(24.dp),
                screenDetail = listOf(
                    ScreenDetail(
                        title = "About",
                        fields = listOf(
                            ScreenDetail.FieldValue("Species", "Seed"),
                            ScreenDetail.FieldValue("Height", "0.70 cm"),
                            ScreenDetail.FieldValue("Weight", "6.9 kg"),
                            ScreenDetail.FieldValue("Abilities", "Chlorophyll")
                        )
                    ),
                    ScreenDetail(
                        title = "Breeding",
                        fields = listOf(
                            ScreenDetail.FieldValue("Gender Male", "87,5%"),
                            ScreenDetail.FieldValue("Gender Female", "12,5%"),
                            ScreenDetail.FieldValue("Egg Groups", "Monster"),
                            ScreenDetail.FieldValue("Egg Cycle", "Grass")
                        ),
                    )
                )
            )
        }

    }
}


@Preview
@Composable
private fun PokemonBaseStatsScreenPreview() {
    PokedexTheme {
        Surface {
            PokemonAboutScreen(
                modifier = Modifier.padding(24.dp),
                screenDetail = listOf(
                    ScreenDetail(
                        title = "Stats",
                        fields = listOf(
                            ScreenDetail.Chart("HP", 45),
                            ScreenDetail.Chart("Attack", 49),
                            ScreenDetail.Chart("Defense", 49),
                            ScreenDetail.Chart("Sp. Atk", 65),
                            ScreenDetail.Chart("Sp. Def", 65),
                            ScreenDetail.Chart("Speed", 45),
                            ScreenDetail.Chart("Total", 318, 600),
                        )
                    )
                )
            )
        }
    }
}

@Preview
@Composable
private fun PokemonMovesScreenPreview() {
    PokedexTheme {
        Surface {
            PokemonAboutScreen(
                modifier = Modifier.padding(24.dp),
                screenDetail = listOf(
                    ScreenDetail(
                        title = "Abilities",
                        fields = listOf(
                            ScreenDetail.SortedValue(14, "Swords-Dance"),
                            ScreenDetail.SortedValue(15, "Cut"),
                            ScreenDetail.SortedValue(20, "Vine-Whip"),
                            ScreenDetail.SortedValue(21, "Fly"),
                            ScreenDetail.SortedValue(22, "Tackle"),
                            ScreenDetail.SortedValue(25, "Body-Slam")
                        )
                    )
                )
            )
        }
    }
}
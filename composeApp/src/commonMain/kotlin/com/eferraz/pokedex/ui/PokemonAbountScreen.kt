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
    data class Chart(val field: String, val value: Int, val maxValue: Int = 100) : FieldTypes
}

@Composable
internal fun PokemonAboutScreen(modifier: Modifier = Modifier, screenDetail: List<ScreenDetail>) {

    Column(modifier = modifier.background(MaterialTheme.colorScheme.background).fillMaxWidth().padding(24.dp), verticalArrangement = spacedBy(8.dp)) {

        screenDetail.forEach { detail ->

            detail.title?.let { title ->
                Text(
                    title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 24.dp, bottom = 4.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            detail.fields.forEach {

                Row(horizontalArrangement = spacedBy(8.dp), verticalAlignment = CenterVertically) {

                    when (it) {
                        is ScreenDetail.Chart -> ChartComponent(field = it.field, value = it.value, maxValue = it.maxValue)
                        is ScreenDetail.FieldValue -> FiledValueComponent(field = it.field, value = it.value)
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.FiledValueComponent(field: String, value: String) {

    Text(
        text = field,
        modifier = Modifier.width(120.dp),
        style = MaterialTheme.typography.bodyMedium
    )

    Text(
        text = value,
        modifier = Modifier.weight(1f),
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun RowScope.ChartComponent(field: String, value: Int, maxValue: Int) {

    Text(
        text = field,
        modifier = Modifier.width(120.dp),
        style = MaterialTheme.typography.bodyMedium
    )

    Text(
        text = value.toString(),
        modifier = Modifier.width(40.dp),
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold
    )

    val statColor = if (value > 50) Color.Green else Color.Red

    LinearProgressIndicator(
        progress = { value.toFloat() / maxValue.toFloat() },
        modifier = Modifier.weight(0.7f).height(4.dp),
        color = statColor,
        trackColor = Color.Gray.copy(alpha = 0.1f)
    )
}

@Preview
@Composable
private fun PokemonAboutScreenPreview() {
    PokedexTheme {
        Box(modifier = Modifier.background(Color.White)) {
            PokemonAboutScreen(
                screenDetail = listOf(
                    ScreenDetail(
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
fun PokemonBaseStatsScreenPreview() {
    MaterialTheme {
        Box(modifier = Modifier.background(Color.White)) {
            PokemonAboutScreen(
                screenDetail = listOf(
                    ScreenDetail(
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
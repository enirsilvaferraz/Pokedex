package com.eferraz.pokedex.ui.detail.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.eferraz.pokedex.helpers.PokedexTheme
import com.eferraz.pokedex.ui.detail.vos.PokemonDetailVo
import com.eferraz.pokedex.ui.preview.pokemon
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun TypeTagsWidget(
    modifier: Modifier = Modifier,
    types: PokemonDetailVo.Types,
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = spacedBy(space = 8.dp, alignment = Alignment.End)
    ) {

        types.types.forEach { name ->

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.3f))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    modifier = Modifier.widthIn(min = 60.dp),
                    text = name,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
private fun TypeTagsPreview(
//    @PreviewParameter(PokemonParamProvider::class) pokemon: PokemonView
) {
    PokedexTheme {
        Surface(color = pokemon.background.color) {
            Box(modifier = Modifier.padding(24.dp)) {
                TypeTagsWidget(modifier = Modifier, types = pokemon.types)
            }
        }
    }
}

package com.eferraz.pokedex.ui.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.eferraz.pokedex.helpers.PokedexTheme
import com.eferraz.pokedex.ui.preview.pokemon
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun CardComponent(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.7f))
    ) {
        Column(modifier = Modifier.padding(24.dp), verticalArrangement = spacedBy(6.dp), content = content)
    }
}

@Preview
@Composable
private fun CardComponentPreview() {
    PokedexTheme {
        Surface(color = pokemon.background.color) {
            CardComponent(modifier = Modifier.padding(24.dp)) {
                Box(Modifier.height(200.dp).fillMaxWidth().background(Color.LightGray))
            }
        }
    }
}

package com.eferraz.pokedex.ui.detail.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal fun CardTitleComponent(
    modifier: Modifier = Modifier,
    title: String,
) {
    Text(
        title,
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier.padding(bottom = 4.dp),
        fontWeight = FontWeight.Bold
    )
}
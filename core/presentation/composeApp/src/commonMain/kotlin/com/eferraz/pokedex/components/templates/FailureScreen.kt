package com.eferraz.pokedex.components.templates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import pokedex.features.composeapp.generated.resources.Res
import pokedex.features.composeapp.generated.resources.detail_error_load
import pokedex.features.composeapp.generated.resources.pokedex_retry

@Composable
internal fun FailureScreen(
    onClick: () -> Unit,
    onContainerColor: Color,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Text(
            text = stringResource(Res.string.detail_error_load),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = onContainerColor
        )

        TextButton(
            onClick = onClick,
            colors = ButtonDefaults.textButtonColors(
                contentColor = onContainerColor
            )
        ) {
            Text(stringResource(Res.string.pokedex_retry))
        }
    }
}

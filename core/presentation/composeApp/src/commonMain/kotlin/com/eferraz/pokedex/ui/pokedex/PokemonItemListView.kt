package com.eferraz.pokedex.ui.pokedex

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.size.Precision
import coil3.size.Size
import com.eferraz.pokedex.components.templates.PokedexTheme
import com.eferraz.pokedex.helpers.capitalizeName
import com.eferraz.pokedex.helpers.formatID
import com.eferraz.pokedex.helpers.toBackground

@Composable
internal fun PokemonItemListView(
    modifier: Modifier = Modifier,
    model: PokemonItemListUi,
    onClick: () -> Unit,
) {

    val latestOnClick by rememberUpdatedState(onClick)

    val background = when (model) {

        is PokemonItemListUi.SummaryRow -> remember(model.type1Name) {
            model.type1Name.toBackground().copy(alpha = 0.3f)
        }

        is PokemonItemListUi.PlaceholderRow -> remember {
            Color.Gray.copy(alpha = 0.12f)
        }
    }

    Surface(
        modifier = modifier.padding(vertical = 4.dp).height(95.dp),
        shape = RoundedCornerShape(12.dp),
        color = background,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
    ) {

        Row(
            modifier = Modifier.fillMaxSize().clickable { latestOnClick() },
        ) {

            Column(
                modifier = Modifier.padding(16.dp).weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {

                    Text(
                        text = model.id.formatID(),
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                    )

                    Text(
                        text = model.name.capitalizeName(),
                        maxLines = 1,
                    )
                }

                when (model) {
                    is PokemonItemListUi.SummaryRow -> TypeTags(type1 = model.type1Name, type2 = model.type2Name)
                    is PokemonItemListUi.PlaceholderRow -> TypeTags(type1 = null, type2 = null)
                }
            }

            ListItemImage(
                url = model.artworkUrl,
                contentDescription = model.name.capitalize(Locale.current),
            )
        }
    }
}

@Composable
private fun ListItemImage(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String,
) {

    val context = LocalPlatformContext.current
    val request = remember(url) {
        ImageRequest.Builder(context)
            .data(url)
            .size(Size(72, 72))
            .precision(Precision.INEXACT)
            .crossfade(false)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .build()
    }

    Box(
        modifier = modifier
            .size(120.dp)
            .clip(RoundedCornerShape(topStart = 50.dp, bottomStart = 50.dp))
            .background(Color.White.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center,
    ) {

        AsyncImage(
            model = request,
            contentDescription = contentDescription,
            contentScale = ContentScale.Fit,
            filterQuality = FilterQuality.Low,
            modifier = Modifier.size(80.dp),
        )
    }
}

@Composable
private fun TypeTags(
    modifier: Modifier = Modifier,
    type1: String?,
    type2: String?,
) {

    if (type1.isNullOrEmpty() && type2.isNullOrEmpty()) {
        Spacer(modifier = modifier.padding(top = 12.dp))
        return
    }

    Row(
        modifier = modifier.padding(top = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        if (!type1.isNullOrEmpty()) {
            TypeChip(label = type1)
        }

        type2?.takeIf { it.isNotEmpty() }?.let {
            TypeChip(label = it)
        }
    }
}

@Composable
private fun TypeChip(
    modifier: Modifier = Modifier,
    label: String,
) {

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(Color.Gray.copy(alpha = 0.2f))
            .widthIn(min = 40.dp),
        contentAlignment = Alignment.Center,
    ) {

        Text(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
            text = label,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
        )
    }
}

@Preview
@Composable
private fun PokemonItemListViewPreview(@PreviewParameter(PokemonListPreviewProvider::class) model: PokemonItemListUi,) {
    PokemonItemListViewPreviewContent(model = model)
}

@Composable
private fun PokemonItemListViewPreviewContent(model: PokemonItemListUi) {
    PokedexTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            PokemonItemListView(
                model = model,
                onClick = {},
            )
        }
    }
}

private class PokemonListPreviewProvider : PreviewParameterProvider<PokemonItemListUi> {
    override val values: Sequence<PokemonItemListUi> = sequenceOf(
        PokemonItemListUi.PlaceholderRow(
            id = 1,
            name = "bulbasaur",
            artworkUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
        ),
        PokemonItemListUi.SummaryRow(
            id = 1,
            name = "bulbasaur",
            type1Name = "grass",
            type2Name = "poison",
            artworkUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
        ),
        PokemonItemListUi.SummaryRow(
            id = 4,
            name = "charmander",
            type1Name = "fire",
            type2Name = null,
            artworkUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png",
        ),
        PokemonItemListUi.SummaryRow(
            id = 25,
            name = "pikachu",
            type1Name = "electric",
            type2Name = null,
            artworkUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
        ),
    )
}

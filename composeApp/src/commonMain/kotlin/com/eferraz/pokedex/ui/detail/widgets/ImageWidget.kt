package com.eferraz.pokedex.ui.detail.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.eferraz.pokedex.helpers.PokedexTheme
import com.eferraz.pokedex.ui.detail.components.CardComponent
import com.eferraz.pokedex.ui.detail.vos.PokemonDetailVo
import com.eferraz.pokedex.ui.preview.pokemon
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun ImageWidget(
    modifier: Modifier = Modifier,
    image: PokemonDetailVo.ImageVo
) {

    CardComponent(modifier = modifier) {

        val internalModifier = Modifier.fillMaxWidth().height(200.dp)

        when (image) {

            is PokemonDetailVo.ImageVo.Local -> Image(
                modifier = internalModifier,
                painter = painterResource(image.resource),
                contentDescription = image.contentDescription
            )

            is PokemonDetailVo.ImageVo.Remote -> AsyncImage(
                modifier = internalModifier,
                model = image.url,
                contentDescription = image.contentDescription
            )
        }
    }
}

@Preview
@Composable
private fun ImageWidgetPreview(
//    @PreviewParameter(PokemonParamProvider::class) pokemon: PokemonView
) {
    PokedexTheme {
        Surface(color = pokemon.background.color) {
            ImageWidget(modifier = Modifier.padding(24.dp), image = pokemon.image)
        }
    }
}

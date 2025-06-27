package com.example.pokedex.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.pokedex.helpers.AppScaffold
import com.example.pokedex.entity.PokemonVO
import com.example.pokedex.extensions.color
import com.example.pokedex.extensions.formatedId
import com.example.pokedex.extensions.formatedName
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider
import org.koin.compose.viewmodel.koinViewModel
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.pokeball

@Composable
internal fun PokedexRoute(
    modifier: Modifier = Modifier,
    vm: PokedexViewModel = koinViewModel(),
    onClick: (Int) -> Unit
) {

    PokedexScreen(
        modifier = modifier,
        pokemonList = vm.pokemonList,
        onClick = onClick
    )
}

@Composable
private fun PokedexScreen(
    modifier: Modifier = Modifier,
    pokemonList: List<PokemonVO>,
    onClick: (Int) -> Unit
) {

    AppScaffold(
        modifier = modifier,
        title = "Kanto"
    ) { innerModifier ->

        LazyColumn(
            modifier = innerModifier,
            contentPadding = PaddingValues(24.dp)
        ) {

            items(items = pokemonList, key = { it.id }) {
                ItemList(model = it, onClick = onClick)
            }
        }
    }
}

@Composable
private fun ItemList(
    modifier: Modifier = Modifier,
    model: PokemonVO,
    onClick: (Int) -> Unit
) {

    Card(
        modifier = modifier.padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = model.color().copy(alpha = 0.3f))
    ) {

        Row(
            modifier = Modifier.fillMaxSize().clickable { onClick(model.id) }
        ) {

            Column(
                modifier = Modifier.padding(16.dp).weight(1f),
                verticalArrangement = Arrangement.Center
            ) {

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(model.formatedId(), fontWeight = FontWeight.Bold)
                    Text(model.formatedName())
                }

                TypeTags(model.types)
            }

            Image(model.url, model.formatedName())
        }
    }
}

@Composable
private fun Image(url: String, contentDescription: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalPlatformContext.current).data(url).crossfade(true).build(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .height(95.dp)
            .width(120.dp)
            .clip(RoundedCornerShape(topStart = 50.dp, bottomStart = 50.dp))
            .background(Color.White.copy(alpha = 0.5f))
    )
}

@Composable
private fun TypeTags(types: List<String>) {

    LazyRow(
        modifier = Modifier.padding(top = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(types) { type ->

            Box(
                modifier = Modifier.clip(RoundedCornerShape(6.dp)).background(Color.Gray.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                    text = type,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
@Preview
private fun PokedexScreenPreview(
    @PreviewParameter(PokedexScreenPreviewProvider::class) model: List<PokemonVO>
) {
    PokedexScreen(Modifier, model, {})
}

private class PokedexScreenPreviewProvider : PreviewParameterProvider<List<PokemonVO>> {
    override val values = sequenceOf(
        listOf(
            PokemonVO(id = 1, name = "Bulbasaur", types = listOf("Grass", "Poison"), url = ""),
            PokemonVO(id = 2, name = "Ivysaur", types = listOf("Grass", "Poison"), url = ""),
            PokemonVO(id = 3, name = "Venusaur", types = listOf("Grass", "Poison"), url = ""),
            PokemonVO(id = 4, name = "Charmander", types = listOf("Fire"), url = ""),
            PokemonVO(id = 5, name = "Charmeleon", types = listOf("Fire"), url = ""),
            PokemonVO(id = 6, name = "Charizard", types = listOf("Fire", "Flying"), url = ""),
            PokemonVO(id = 7, name = "Squirtle", types = listOf("Water"), url = ""),
            PokemonVO(id = 8, name = "Wartortle", types = listOf("Water"), url = ""),
            PokemonVO(id = 9, name = "Blastoise", types = listOf("Water"), url = ""),
            PokemonVO(id = 10, name = "Caterpie", types = listOf("Bug"), url = "")
        )
    )
}

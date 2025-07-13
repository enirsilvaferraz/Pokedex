package com.eferraz.pokedex.ui

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
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
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import app.cash.paging.compose.itemKey
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.eferraz.pokedex.entity.PokemonVO
import com.eferraz.pokedex.entity.TypeVO
import com.eferraz.pokedex.helpers.AppScaffold
import com.eferraz.pokedex.helpers.color
import com.eferraz.pokedex.helpers.formatedId
import com.eferraz.pokedex.helpers.formatedName
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun PokedexRoute(
    modifier: Modifier = Modifier,
    vm: PokedexViewModel = koinViewModel(),
    onClick: (PokemonVO) -> Unit,
) {

    val list = vm.flow.collectAsLazyPagingItems()

    PokedexScreen(
        modifier = modifier,
        pokemonList = list,
        onClick = onClick
    )
}

@Composable
private fun PokedexScreen(
    modifier: Modifier = Modifier,
    pokemonList: LazyPagingItems<PokemonVO>,
    onClick: (PokemonVO) -> Unit,
) {

    AppScaffold(
        modifier = modifier,
        title = "Pokedex"
    ) { innerModifier ->

        LazyColumn(
            modifier = innerModifier,
            contentPadding = PaddingValues(24.dp)
        ) {

            items(pokemonList.itemCount, key = pokemonList.itemKey { it.id }) { index ->
                pokemonList[index]?.let { item ->
                    ItemList(model = item, onClick = onClick)
                }
            }
        }
    }
}

@Composable
private fun ItemList(
    modifier: Modifier = Modifier,
    model: PokemonVO,
    onClick: (PokemonVO) -> Unit,
) {

    Card(
        modifier = modifier.padding(vertical = 4.dp).height(95.dp),
        colors = CardDefaults.cardColors(containerColor = model.color().copy(alpha = 0.3f))
    ) {

        Row(
            modifier = Modifier.fillMaxSize().clickable { onClick(model) }
        ) {

            Column(
                modifier = Modifier.padding(16.dp).weight(1f),
                verticalArrangement = Arrangement.Center
            ) {

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(model.formatedId(), fontWeight = FontWeight.Bold)
                    Text(model.formatedName())
                }

                TypeTags(listOf(model.type1, model.type2).mapNotNull { it })
            }

            Image(model.image, model.formatedName())
        }
    }
}

@Composable
private fun Image(url: String, contentDescription: String) {
    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(RoundedCornerShape(topStart = 50.dp, bottomStart = 50.dp))
            .background(Color.White.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalPlatformContext.current).data(url).crossfade(true).build(),
            contentDescription = contentDescription,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(80.dp)
                .width(80.dp)
        )
    }
}

@Composable
private fun TypeTags(types: List<TypeVO>) {

    LazyRow(
        modifier = Modifier.padding(top = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(types) { typeVO ->

            if (typeVO.name.isEmpty()) return@items

            Box(
                modifier = Modifier.clip(RoundedCornerShape(6.dp)).background(Color.Gray.copy(alpha = 0.2f)).widthIn(min = 40.dp),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                    text = typeVO.name,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
@Preview
private fun PokedexScreenPreview(
    @PreviewParameter(PokedexScreenPreviewProvider::class) model: List<PokemonVO>,
) {
//    PokedexScreen(Modifier, model, {})
}

private class PokedexScreenPreviewProvider : PreviewParameterProvider<List<PokemonVO>> {
    override val values = sequenceOf(
        listOf<PokemonVO>(
//            PokemonVO(id = 1, name = "Bulbasaur", type1 = TypeVO(0L, "Grass"), type2 = TypeVO(0L, "Poison"), image = ""),
//            PokemonVO(id = 2, name = "Ivysaur", type1 = TypeVO(0L, "Grass"), type2 = TypeVO(0L, "Poison"), image = ""),
//            PokemonVO(id = 3, name = "Venusaur", type1 = TypeVO(0L, "Grass"), type2 = TypeVO(0L, "Poison"), image = ""),
//            PokemonVO(id = 4, name = "Charmander", type1 = TypeVO(0L, "Fire"), image = ""),
//            PokemonVO(id = 5, name = "Charmeleon", type1 = TypeVO(0L, "Fire"), image = ""),
//            PokemonVO(id = 6, name = "Charizard", type1 = TypeVO(0L, "Fire"), type2 = TypeVO(0L, "Flying"), image = ""),
//            PokemonVO(id = 7, name = "Squirtle", type1 = TypeVO(0L, "Water"), image = ""),
//            PokemonVO(id = 8, name = "Wartortle", type1 = TypeVO(0L, "Water"), image = ""),
//            PokemonVO(id = 9, name = "Blastoise", type1 = TypeVO(0L, "Water"), image = ""),
//            PokemonVO(id = 10, name = "Caterpie", type1 = TypeVO(0L, "Bug"), image = "")
        )
    )
}

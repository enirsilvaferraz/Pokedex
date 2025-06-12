package com.example.pokedex

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.request.crossfade
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.pokeball

// You'll need to provide the ViewModel instance.
// This is a placeholder, in a real app you'd use a ViewModel provider (e.g., Hilt, Koin, or manual)
val pokemonViewModel = PokemonViewModel()

@Composable
fun App() {
    val pokemonList by pokemonViewModel.pokemonList.collectAsState()

    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            background = Color.White,
            surface = Color.White
        )
    ) {
        AppScaffold(title = "Kanto") {
            CollectionScreen(
                modifier = it,
                list = pokemonList,
                onItem = {
                    ItemList(model = it) {
                        // navigate to pokemon
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    title: String,
    content: @Composable (Modifier) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        title,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            )
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(
            top = innerPadding.calculateTopPadding(),
            start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
            end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
        )
        content(modifier)
    }
}

@Composable
fun CollectionScreen(
    modifier: Modifier,
    list: List<PokemonVO>,
    onItem: @Composable (PokemonVO) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(24.dp)
    ) {
        items(list) {
            onItem(it)
        }
    }
}


data class PokemonVO(
    val color: Color = Color.LightGray,
    private val id: Int,
    val name: String,
    val types: List<String> = emptyList(),
    val url: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png",
) {
    fun formatedId() = formatId(id)
}

@Composable
private fun ItemList(
    model: PokemonVO,
    onClick: (PokemonVO) -> Unit
) {
    Card(
        modifier = Modifier.padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = model.color.copy(alpha = 0.3f))
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
                    Text(model.name)
                }
                TypeTags(model.types)
            }
            Image(model.url, model.name)
        }
    }
}

@Composable
private fun Image(url: String, contentDescription: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalPlatformContext.current).data(url).crossfade(true).build(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit,
        placeholder = painterResource(Res.drawable.pokeball),
        error = painterResource(Res.drawable.pokeball),
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
fun AppPreview() {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            background = Color.White,
            surface = Color.White
        )
    ) {
        AppScaffold(title = "Kanto") {
            CollectionScreen(
                modifier = it,
                list = previewGetList(), // Use a dedicated preview list
                onItem = {
                    ItemList(model = it) {
                        // navigate to pokemon
                    }
                }
            )
        }
    }
}


// Keep this for the @Preview or provide a mock ViewModel
private fun previewGetList(): ArrayList<PokemonVO> = arrayListOf<PokemonVO>().apply {
    repeat(10) { // Reduced for preview
        add(
            PokemonVO(
                color = listOf(
                    Color.Green, Color.Red, Color.Blue
                ).random(),
                id = it + 1,
                name = "SampleMon",
                types = listOf("Type A", "Type B")
            )
        )
    }
}

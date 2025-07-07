package com.example.pokedex.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import com.example.pokedex.entity.PokemonVO
import com.example.pokedex.entity.TypeVO
import com.example.pokedex.helpers.PokedexTheme
import com.example.pokedex.helpers.color
import com.example.pokedex.helpers.edgeToEdgePadding
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.pokeball

@Composable
internal fun PokemonRoute(
    modifier: Modifier = Modifier,
    id: Long,
    onNavigateBack: () -> Unit = {},
) {

    val vm: PokemonViewModel = koinViewModel(parameters = { parametersOf(id) })
    val state by vm.state.collectAsState()

    PokemonScreen(
        modifier = modifier,
        state = state,
        onNavigateBack = onNavigateBack
    )
}

@Composable
private fun PokemonScreen(modifier: Modifier, state: PokemonViewModel.State, onNavigateBack: () -> Unit) {

    PokedexTheme {

        Scaffold(
            modifier = modifier,
            topBar = {

                @OptIn(ExperimentalMaterial3Api::class)
                TopAppBar(
                    title = { Text("Bulbasaur") },
                    navigationIcon = {
                        IconButton(onClick = onNavigateBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Navigate Back",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
                )
            },
            containerColor = (state as? PokemonViewModel.Success)?.model?.color() ?: Color.Transparent
        ) {

            Column(modifier = Modifier.edgeToEdgePadding(it, LocalLayoutDirection.current)) {

                Header(state)

                Card(
                    modifier = Modifier.padding(top = 0.dp).fillMaxSize(),
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {

                    when (state) {
                        is PokemonViewModel.Loading -> {}
                        is PokemonViewModel.Success -> SuccessScreen(state.model)
                        is PokemonViewModel.Error -> {}
                    }
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.Header(state: PokemonViewModel.State) {

    when (state) {

        is PokemonViewModel.Loading -> {}

        is PokemonViewModel.Success ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .zIndex(1f)
                    .offset(y = 40.dp),
                contentAlignment = Alignment.BottomCenter
            ) {

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                    Image(
                        painter = painterResource(Res.drawable.pokeball),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color.White),
                        modifier = Modifier.alpha(0.2f).rotate(30f).size(280.dp)
                    )
                }

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomCenter) {
                    AsyncImage(
                        model = pokemon.image,
                        contentDescription = pokemon.name,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(220.dp)
                    )
                }
            }

        is PokemonViewModel.Error -> {}
    }
}

@Composable
private fun ColumnScope.SuccessScreen(model: PokemonVO) {

}

@Preview
@Composable
private fun PokemonScreenPreview() {
    PokemonScreen(
        modifier = Modifier,
        state = PokemonViewModel.Success(pokemon),
        onNavigateBack = {}
    )
}

internal val pokemon = PokemonVO(
    id = 1,
    name = "Bulbasaur",
    image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
    type1 = TypeVO(0, "Grass"),
    type2 = TypeVO(0, "Poison"),
    height = 0.7f,
    weight = 6.9f,
    description = "Bulbasaur can be seen napping in bright sunlight. There is a seed on its back. By soaking up the sun's rays, the seed grows progressively larger.",
    category = "Seed",
//        abilities = listOf("Overgrow", "Chlorophyll"),
    genderRatioMale = 0.8f,
    primaryColor = "Green",

//        stats = listOf(
//            PokemonVO.Stat(label = "HP", value = 45, percentage = 0.45f),
//            PokemonVO.Stat(label = "Attack", value = 49, percentage = 0.49f),
//            PokemonVO.Stat(label = "Defense", value = 49, percentage = 0.49f),
//            PokemonVO.Stat(label = "Special Attack", value = 65, percentage = 0.65f),
//            PokemonVO.Stat(label = "Special Defense", value = 65, percentage = 0.65f),
//            PokemonVO.Stat(label = "Speed", value = 45, percentage = 0.45f)
//        )
)

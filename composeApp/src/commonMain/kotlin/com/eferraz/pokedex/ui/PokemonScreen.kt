package com.eferraz.pokedex.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.eferraz.pokedex.entity.PokemonVO
import com.eferraz.pokedex.entity.TypeVO
import com.eferraz.pokedex.helpers.PokedexTheme
import com.eferraz.pokedex.helpers.color
import com.eferraz.pokedex.helpers.edgeToEdgePadding
import com.eferraz.pokedex.ui.ScreenDetail.FieldValue
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PokemonScreen(modifier: Modifier, state: PokemonViewModel.State, onNavigateBack: () -> Unit) {

    PokedexTheme {

        when (state) {
            PokemonViewModel.Error -> {} // TODO implementar tela de error
            PokemonViewModel.Loading -> {} // TODO implementar tela de loading
            is PokemonViewModel.Success -> SuccessScreen(modifier, state.model, onNavigateBack)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SuccessScreen(
    modifier: Modifier,
    model: PokemonVO,
    onNavigateBack: () -> Unit,
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(model.name.capitalize(Locale.current), color = Color.White)
                },
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
        containerColor = model.color()
    ) {

        Column(
            modifier = Modifier.edgeToEdgePadding(it, LocalLayoutDirection.current)
        ) {

            Header(Modifier.weight(1f), model)

            Card(
                modifier = Modifier.padding(horizontal = 24.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Text(
                    modifier = Modifier.padding(24.dp).fillMaxWidth(),
                    text = model.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            val state = rememberPagerState(initialPage = 0, pageCount = { 3 })

            HorizontalPager(
                modifier = Modifier.padding(bottom = 24.dp).height(333.dp),
                state = state,
                contentPadding = PaddingValues(24.dp),
                pageSpacing = 24.dp
            ) {

                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {

                    when (it) {
                        0 -> AboutScreen(Modifier.padding(24.dp).fillMaxSize())
                        1 -> StatsScreen(Modifier.padding(24.dp).fillMaxSize())
                        2 -> MovesScreen(Modifier.padding(24.dp).fillMaxSize())
                    }
                }
            }
        }
    }
}

@Composable
private fun AboutScreen(
    modifier: Modifier = Modifier,
) {
    PokemonAboutScreen(
        modifier = modifier.fillMaxWidth(), listOf(
            ScreenDetail(
                title = "About",
                fields = listOf(
                    FieldValue("Species", "Seed"),
                    FieldValue("Height", "0.70 cm"),
                    FieldValue("Weight", "6.9 kg"),
//                    FieldValue("Abilities", "Chlorophyll")
                )
            ),
            ScreenDetail(
                title = "Breeding",
                fields = listOf(
                    FieldValue("Gender", "87,5% Male, 12,5% Female"),
                    FieldValue("Egg Groups", "Monster"),
                    FieldValue("Egg Cycle", "Grass")
                )
            )
        )
    )
}

@Composable
fun StatsScreen(
    modifier: Modifier = Modifier,
) {
    PokemonAboutScreen(
        modifier = modifier.fillMaxWidth(), listOf(
            ScreenDetail(
                title = "Stats",
                fields = listOf(
                    ScreenDetail.Chart("HP", 45),
                    ScreenDetail.Chart("Attack", 49),
                    ScreenDetail.Chart("Defense", 49),
                    ScreenDetail.Chart("Sp. Atk", 65),
                    ScreenDetail.Chart("Sp. Def", 65),
                    ScreenDetail.Chart("Speed", 45),
                    ScreenDetail.Chart("Total", 318, 600),
                )
            )
        )
    )
}

@Composable
fun MovesScreen(
    modifier: Modifier = Modifier,
) {
    PokemonAboutScreen(
        modifier = modifier.fillMaxSize(), listOf(
            ScreenDetail(
                title = "Abilities",
                fields = listOf(
                    ScreenDetail.SortedValue(14, "Swords-Dance"),
                    ScreenDetail.SortedValue(15, "Cut"),
                    ScreenDetail.SortedValue(20, "Vine-Whip"),
                    ScreenDetail.SortedValue(21, "Fly"),
                    ScreenDetail.SortedValue(22, "Tackle"),
                    ScreenDetail.SortedValue(25, "Body-Slam")
                )
            )
        )
    )
}

@Composable
private fun ColumnScope.Header(
    modifier: Modifier = Modifier,
    model: PokemonVO,
) {

    Row(horizontalArrangement = spacedBy(8.dp), modifier = modifier.padding(horizontal = 24.dp)) {
        TypeTag(model.type1.name, Color.White)
        model.type2?.let {
            TypeTag(it.name, Color.White)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally),
        contentAlignment = Alignment.BottomCenter
    ) {

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            Image(
                painter = painterResource(Res.drawable.pokeball),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                alpha = 0.1f,
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.rotate(30f).size(240.dp)
            )
        }

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomCenter) {
            AsyncImage(
                model = model.image,
                contentDescription = model.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(240.dp),
                alignment = Alignment.BottomCenter
            )
        }
    }
}

@Composable
private fun TypeTag(typeName: String, typeColor: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(typeColor.copy(alpha = 0.3f))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = typeName,
            color = typeColor, // Or a contrasting color if typeColor is too light
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
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

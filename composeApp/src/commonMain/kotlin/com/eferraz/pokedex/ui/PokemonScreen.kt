package com.eferraz.pokedex.ui

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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.eferraz.pokedex.entity.PokemonVO
import com.eferraz.pokedex.entity.TypeVO
import com.eferraz.pokedex.helpers.PokedexTheme
import com.eferraz.pokedex.helpers.color
import com.eferraz.pokedex.helpers.edgeToEdgePadding
import com.eferraz.pokedex.helpers.formatedId
import com.eferraz.pokedex.ui.ScreenDetail.FieldValue
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

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

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { TopBarScreen(model, onNavigateBack, scrollBehavior) },
        containerColor = model.color()
    ) {

        LazyColumn(
            modifier = Modifier.edgeToEdgePadding(it, LocalLayoutDirection.current),
            verticalArrangement = spacedBy(16.dp),
            contentPadding = PaddingValues(start = 24.dp, end = 24.dp, top = 0.dp, bottom = 32.dp)
        ) {

            item { TypeTags(model) }

            card { Image(model) }

            card { Text(text = model.description, style = MaterialTheme.typography.bodyMedium) }

            card { AboutScreen() }

            card { StatsScreen() }

            card { MovesScreen() }
        }
    }
}

private fun LazyListScope.card(content: @Composable ColumnScope.() -> Unit) = item {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.7f))
    ) {
        Column(Modifier.padding(24.dp).fillMaxSize(), content = content)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarScreen(
    model: PokemonVO,
    onNavigateBack: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    MediumTopAppBar(
        title = {
            Row {
                Text(model.name.capitalize(Locale.current), color = Color.White, modifier = Modifier.weight(1f))
                Text(model.formatedId(), color = Color.White, modifier = Modifier.padding(end = 24.dp))
            }
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
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent,
        ),
        scrollBehavior = scrollBehavior
    )
}

@Composable
private fun Image(model: PokemonVO) {
    AsyncImage(
        model = model.image,
        contentDescription = model.name,
        modifier = Modifier.fillMaxWidth().height(200.dp),
        alignment = Alignment.Center
    )
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
                    FieldValue("Abilities", "Chlorophyll")
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
private fun StatsScreen(
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
private fun MovesScreen(
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
private fun TypeTags(model: PokemonVO) {
    Row(
        horizontalArrangement = spacedBy(space = 8.dp, alignment = Alignment.End),
        modifier = Modifier.fillMaxWidth()
    ) {
        model.types().forEach {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.3f))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    modifier = Modifier.widthIn(min = 60.dp),
                    text = it.name.capitalize(Locale.current),
                    color = Color.White, // Or a contrasting color if typeColor is too light
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
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

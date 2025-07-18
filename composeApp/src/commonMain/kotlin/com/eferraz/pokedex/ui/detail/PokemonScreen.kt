package com.eferraz.pokedex.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.eferraz.pokedex.helpers.PokedexTheme
import com.eferraz.pokedex.helpers.edgeToEdgePadding
import com.eferraz.pokedex.ui.detail.ScreenDetail.FieldValue
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
            is PokemonViewModel.Success -> SuccessScreen(modifier, state.vo, onNavigateBack)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SuccessScreen(
    modifier: Modifier,
    vo: PokemonView,
    onNavigateBack: () -> Unit,
) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { TopBarScreen(vo, onNavigateBack, scrollBehavior) },
        containerColor = vo.color
    ) {

        LazyColumn(
            modifier = Modifier.edgeToEdgePadding(it, LocalLayoutDirection.current),
            verticalArrangement = spacedBy(16.dp),
            contentPadding = PaddingValues(start = 24.dp, end = 24.dp, top = 0.dp, bottom = 32.dp)
        ) {

            item { TypeTagsComponent(vo = vo) }

            item { ImageComponent(vo = vo) }

            item { DescriptionComponent(vo = vo) }

            item { AboutComponent(vo = vo) }

            item { StatsComponent(vo = vo.stats) }

            item { MovesComponent(vo = vo) }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBarScreen(
    vo: PokemonView,
    onNavigateBack: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    MediumTopAppBar(
        title = {
            Row {
                Text(vo.name, color = Color.White, modifier = Modifier.weight(1f))
                Text(vo.id, color = Color.White, modifier = Modifier.padding(end = 24.dp))
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
private fun CardSlot(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.(Modifier) -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.7f))
    ) {
        content(Modifier.padding(24.dp))
    }
}

@Composable
private fun TypeTagsComponent(
    modifier: Modifier = Modifier,
    vo: PokemonView,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = spacedBy(space = 8.dp, alignment = Alignment.End)
    ) {

        vo.types.forEach { name ->

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.3f))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    modifier = Modifier.widthIn(min = 60.dp),
                    text = name,
                    color = Color.White, // Or a contrasting color if typeColor is too light
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun ImageComponent(
    modifier: Modifier = Modifier,
    vo: PokemonView,
) {
    CardSlot(modifier = modifier) { modifier ->
        AsyncImage(
            model = vo.image,
            contentDescription = vo.name,
            modifier = modifier.fillMaxWidth().height(200.dp),
            alignment = Alignment.Center
        )
    }
}

@Composable
private fun DescriptionComponent(
    modifier: Modifier = Modifier,
    vo: PokemonView,
) {
    CardSlot(modifier = modifier) { modifier ->
        Text(modifier = modifier, text = vo.description, style = MaterialTheme.typography.bodyMedium)
    }
}


@Composable
private fun CardTitleComponent(title: String) {
    Text(
        title,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(bottom = 4.dp),
        fontWeight = FontWeight.Bold
    )
}


@Composable
private fun RowScope.FieldValueComponent(field: String, value: String) {

    Text(
        modifier = Modifier.width(110.dp),
        text = field,
        style = MaterialTheme.typography.bodyMedium
    )

    Text(
        modifier = Modifier.weight(1f),
        text = value,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun ChartComponent(field: String, value: String, color: Color, progress: Float) {

    Row(verticalAlignment = CenterVertically) {

        Text(
            modifier = Modifier.width(110.dp),
            text = field,
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            modifier = Modifier.width(40.dp),
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )

        LinearProgressIndicator(
            modifier = Modifier.weight(0.7f).height(4.dp),
            progress = { progress },
            color = color,
            trackColor = Color.Gray.copy(alpha = 0.1f)
        )
    }
}

@Composable
private fun RowScope.SortedValueComponent(field: ScreenDetail.SortedValue) {

    Text(
        modifier = Modifier.padding(end = 8.dp),
        text = field.number.toString() + ".",
        style = MaterialTheme.typography.bodyMedium
    )

    Text(
        modifier = Modifier.weight(1f),
        text = field.text,
        style = MaterialTheme.typography.bodyMedium,
    )
}


@Composable
private fun AboutComponent(
    modifier: Modifier = Modifier,
    vo: PokemonView,
) {
    CardSlot(modifier = modifier) { modifier ->
        PokemonAboutScreen(
            modifier = modifier, listOf(
                ScreenDetail(
                    title = "About",
                    fields = listOf(
                        FieldValue("Species", vo.about.species),
                        FieldValue("Category", vo.about.category),
                        FieldValue("Height", vo.about.height),
                        FieldValue("Weight", vo.about.weight),
                        FieldValue("Abilities", vo.about.abilities)
                    )
                ),
                ScreenDetail(
                    title = "Breeding",
                    fields = listOf(
                        FieldValue("Gender", vo.breeding.gender),
                        FieldValue("Egg Groups", vo.breeding.eggGroups),
                        FieldValue("Egg Cycle", vo.breeding.eggCycle)
                    )
                )
            )
        )
    }
}

@Composable
private fun StatsComponent(
    modifier: Modifier = Modifier,
    vo: PokemonView.Stats,
) {
    CardSlot(modifier = modifier) { modifier ->

        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = spacedBy(6.dp)
        ) {

            CardTitleComponent("Stats")

            listOf(
                "HP" to vo.hp,
                "Attack" to vo.attack,
                "Defense" to vo.defense,
                "Sp. Atk" to vo.spAtk,
                "Sp. Def" to vo.spDef,
                "Speed" to vo.speed,
                "Total" to vo.total
            ).forEach { (field, value) ->

                Row(verticalAlignment = CenterVertically) {

                    Text(
                        modifier = Modifier.width(110.dp),
                        text = field,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Text(
                        modifier = Modifier.width(40.dp),
                        text = value.text(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )

                    LinearProgressIndicator(
                        modifier = Modifier.weight(0.7f).height(4.dp),
                        progress = { value.progress() },
                        color = value.color(),
                        trackColor = Color.Gray.copy(alpha = 0.1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun MovesComponent(
    modifier: Modifier = Modifier,
    vo: PokemonView,
) {
    CardSlot(modifier = modifier) { modifier ->
        PokemonAboutScreen(
            modifier = modifier, listOf(
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
}

/**
 * Preview Area
 */

@Preview
@Composable
private fun PokemonScreenPreview() {
    PokemonScreen(
        modifier = Modifier,
        state = PokemonViewModel.Success(pokemon),
        onNavigateBack = {}
    )
}

@Preview
@Composable
private fun PokemonScreenTagsPreview() {
    PokedexTheme {
        Surface(color = pokemon.color) {
            Box(modifier = Modifier.padding(24.dp)) {
                TypeTagsComponent(modifier = Modifier, vo = pokemon)
            }
        }
    }
}

@Preview
@Composable
private fun PokemonScreenAboutPreview() {
    PokedexTheme {
        Surface(color = pokemon.color) {
            Box(modifier = Modifier.padding(24.dp)) {
                AboutComponent(modifier = Modifier, vo = pokemon)
            }
        }
    }
}

@Preview
@Composable
private fun PokemonScreenStatsPreview() {
    PokedexTheme {
        Surface(color = pokemon.color) {
            Box(modifier = Modifier.padding(24.dp)) {
                StatsComponent(modifier = Modifier, vo = pokemon.stats)
            }
        }
    }
}

@Preview
@Composable
private fun PokemonScreenMovesPreview() {
    PokedexTheme {
        Surface(color = pokemon.color) {
            Box(modifier = Modifier.padding(24.dp)) {
                MovesComponent(modifier = Modifier, vo = pokemon)
            }
        }
    }
}

private val pokemon = PokemonView(
    id = "#001",
    name = "Bulbasaur",
    types = listOf("Grass", "Poison"),
    image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
    description = "Bulbasaur can be seen napping in bright sunlight. There is a seed on its back. By soaking up the sun's rays, the seed grows progressively larger.",
    color = Color(0xFF7AC74C),
    about = PokemonView.About(
        species = "Seed Pokemon",
        category = "Quadruped",
        height = "0.70 cm",
        weight = "6.9 kg",
        abilities = "Chlorophyll, Overgrow"
    ),
    breeding = PokemonView.Breeding(
        gender = "87,5% Male, 12,5% Female",
        eggGroups = "Monster",
        eggCycle = "Grass"
    ),
    stats = PokemonView.Stats(
        hp = PokemonView.Stats.Item(45),
        attack = PokemonView.Stats.Item(49),
        defense = PokemonView.Stats.Item(4),
        spAtk = PokemonView.Stats.Item(65),
        spDef = PokemonView.Stats.Item(65),
        speed = PokemonView.Stats.Item(45)
    )
)
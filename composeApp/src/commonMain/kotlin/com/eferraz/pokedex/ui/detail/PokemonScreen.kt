package com.eferraz.pokedex.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider
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
        topBar = { TopBar(title = vo.name, id = vo.id, onNavigateBack = onNavigateBack, scrollBehavior = scrollBehavior) },
        containerColor = vo.color
    ) {

        LazyColumn(
            modifier = Modifier.edgeToEdgePadding(it, LocalLayoutDirection.current),
            verticalArrangement = spacedBy(16.dp),
            contentPadding = PaddingValues(start = 24.dp, end = 24.dp, top = 0.dp, bottom = 32.dp)
        ) {

            item { TypeTagsWidget(types = vo.types) }

            item { ImageWidget(image = vo.image, contentDescription = vo.name) }

            item { DescriptionWidget(description = vo.description) }

            item { AboutWidget(about = vo.about, breeding = vo.breeding) }

            item { StatsWidget(stats = vo.stats) }

            item { MovesWidget(abilities = vo.abilities) }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    title: String,
    id: String,
    onNavigateBack: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    MediumTopAppBar(
        title = {
            Row {
                Text(title, color = Color.White, modifier = Modifier.weight(1f))
                Text(id, color = Color.White, modifier = Modifier.padding(end = 24.dp))
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

/**
 * Widget Area
 */

@Composable
private fun TypeTagsWidget(
    modifier: Modifier = Modifier,
    types: List<String>,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = spacedBy(space = 8.dp, alignment = Alignment.End)
    ) {

        types.forEach { name ->

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.3f))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    modifier = Modifier.widthIn(min = 60.dp),
                    text = name,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun ImageWidget(
    modifier: Modifier = Modifier,
    image: String,
    contentDescription: String,
) {
    CardComponent(modifier = modifier) { modifier ->
        AsyncImage(
            modifier = modifier.fillMaxWidth().height(200.dp),
            model = image,
            contentDescription = contentDescription,
            alignment = Alignment.Center
        )
    }
}

@Composable
private fun DescriptionWidget(
    modifier: Modifier = Modifier,
    description: String,
) {
    CardComponent(modifier = modifier) { modifier ->
        Text(modifier = modifier, text = description, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
private fun AboutWidget(
    modifier: Modifier = Modifier,
    about: PokemonView.About,
    breeding: PokemonView.Breeding,
) {
    CardComponent(modifier = modifier) { modifier ->
        PokemonAboutScreen(
            modifier = modifier, listOf(
                ScreenDetail(
                    title = "About",
                    fields = listOf(
                        FieldValue("Species", about.species),
                        FieldValue("Category", about.category),
                        FieldValue("Height", about.height),
                        FieldValue("Weight", about.weight),
                        FieldValue("Abilities", about.abilities)
                    )
                ),
                ScreenDetail(
                    title = "Breeding",
                    fields = listOf(
                        FieldValue("Gender", breeding.gender),
                        FieldValue("Egg Groups", breeding.eggGroups),
                        FieldValue("Egg Cycle", breeding.eggCycle)
                    )
                )
            )
        )
    }
}

@Composable
private fun StatsWidget(
    modifier: Modifier = Modifier,
    stats: PokemonView.Stats,
) {
    CardComponent(modifier = modifier) { modifier ->

        Column(modifier = modifier, verticalArrangement = spacedBy(6.dp)) {

            CardTitleComponent("Stats")

            stats.items().forEach { (field, value) ->

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
private fun MovesWidget(
    modifier: Modifier = Modifier,
    abilities: List<PokemonView.Ability>,
) {
    CardComponent(modifier = modifier) { modifier ->

        Column(modifier = modifier, verticalArrangement = spacedBy(6.dp)) {

            CardTitleComponent("Abilities")

            FlowRow(
                maxItemsInEachRow = 2,
                horizontalArrangement = spacedBy(8.dp),
                verticalArrangement = spacedBy(8.dp),
            ) {

                abilities.forEach { (key, value) ->

                    Row(modifier = Modifier.weight(0.5f)) {

                        Text(
                            modifier = Modifier.padding(end = 8.dp),
                            text = "$key.",
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Text(
                            modifier = Modifier.weight(1f),
                            text = value,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
        }
    }
}

/**
 * Components Area
 */

@Composable
private fun CardComponent(
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
private fun CardTitleComponent(title: String) {
    Text(
        title,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(bottom = 4.dp),
        fontWeight = FontWeight.Bold
    )
}

/**
 * Preview Area
 */

@Preview
@Composable
private fun PokemonScreenPreview(
//    @PreviewParameter(PokemonParamProvider::class) pokemon: PokemonView
) {
    PokemonScreen(
        modifier = Modifier,
        state = PokemonViewModel.Success(pokemon),
        onNavigateBack = {}
    )
}

@Preview
@Composable
private fun PokemonScreenTagsPreview(
//    @PreviewParameter(PokemonParamProvider::class) pokemon: PokemonView
) {
    PokedexTheme {
        Surface(color = pokemon.color) {
            Box(modifier = Modifier.padding(24.dp)) {
                TypeTagsWidget(modifier = Modifier, types = pokemon.types)
            }
        }
    }
}

@Preview
@Composable
private fun PokemonScreenAboutPreview(
//    @PreviewParameter(PokemonParamProvider::class) pokemon: PokemonView
) {
    PokedexTheme {
        Surface(color = pokemon.color) {
            Box(modifier = Modifier.padding(24.dp)) {
                AboutWidget(modifier = Modifier, about = pokemon.about, breeding = pokemon.breeding)
            }
        }
    }
}

@Preview
@Composable
private fun PokemonScreenStatsPreview(
//    @PreviewParameter(PokemonParamProvider::class) pokemon: PokemonView
) {
    PokedexTheme {
        Surface(color = pokemon.color) {
            Box(modifier = Modifier.padding(24.dp)) {
                StatsWidget(modifier = Modifier, stats = pokemon.stats)
            }
        }
    }
}

@Preview
@Composable
private fun PokemonScreenMovesPreview(
//    @PreviewParameter(PokemonParamProvider::class) pokemon: PokemonView
) {
    PokedexTheme {
        Surface(color = pokemon.color) {
            Box(modifier = Modifier.padding(24.dp)) {
                MovesWidget(modifier = Modifier, abilities = pokemon.abilities)
            }
        }
    }
}

internal class PokemonParamProvider() : PreviewParameterProvider<PokemonView> {
    override val values: Sequence<PokemonView> = sequenceOf(pokemon)
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
    ),
    abilities = listOf(
        PokemonView.Ability(14L, "Swords-Dance"),
        PokemonView.Ability(15L, "Cut"),
        PokemonView.Ability(20L, "Vine-Whip"),
        PokemonView.Ability(21L, "Fly"),
        PokemonView.Ability(22L, "Tackle"),
        PokemonView.Ability(25L, "Body-Slam")
    )
)

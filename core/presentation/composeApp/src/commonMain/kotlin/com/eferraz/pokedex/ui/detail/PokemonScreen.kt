package com.eferraz.pokedex.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.eferraz.pokedex.components.templates.AppScaffold
import com.eferraz.pokedex.components.templates.FailureScreen
import com.eferraz.pokedex.components.templates.LoadingScreen
import com.eferraz.pokedex.components.templates.PokedexTheme
import com.eferraz.pokedex.core.params.PokemonSummaryParam
import com.eferraz.pokedex.ui.detail.components.CardComponent
import com.eferraz.pokedex.ui.detail.components.CardTitleComponent
import com.eferraz.pokedex.ui.detail.components.TypeTagsWidget
import com.eferraz.pokedex.ui.detail.data.PokemonDetailDataView
import com.eferraz.pokedex.ui.detail.data.PokemonDetailStructureView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import pokedex.features.composeapp.generated.resources.Res
import pokedex.features.composeapp.generated.resources.detail_about
import pokedex.features.composeapp.generated.resources.detail_about_abilities
import pokedex.features.composeapp.generated.resources.detail_about_category
import pokedex.features.composeapp.generated.resources.detail_about_height
import pokedex.features.composeapp.generated.resources.detail_about_species
import pokedex.features.composeapp.generated.resources.detail_about_weight
import pokedex.features.composeapp.generated.resources.detail_breeding
import pokedex.features.composeapp.generated.resources.detail_breeding_egg_groups
import pokedex.features.composeapp.generated.resources.detail_breeding_female
import pokedex.features.composeapp.generated.resources.detail_breeding_male
import pokedex.features.composeapp.generated.resources.detail_moves
import pokedex.features.composeapp.generated.resources.detail_stats
import pokedex.features.composeapp.generated.resources.detail_stats_atk
import pokedex.features.composeapp.generated.resources.detail_stats_def
import pokedex.features.composeapp.generated.resources.detail_stats_hp
import pokedex.features.composeapp.generated.resources.detail_stats_sp_atk
import pokedex.features.composeapp.generated.resources.detail_stats_sp_def
import pokedex.features.composeapp.generated.resources.detail_stats_speed
import pokedex.features.composeapp.generated.resources.detail_stats_total

@Composable
internal fun PokemonScreen(
    modifier: Modifier = Modifier,
    ref: PokemonSummaryParam,
    onNavigateBack: () -> Unit = {},
) {

    val vm: PokemonViewModel = koinViewModel(parameters = { parametersOf(ref) })

    PokemonScreen(
        modifier = modifier,
        stateFlow = vm.state,
        onNavigateBack = onNavigateBack,
        onIntent = remember { { vm.dispatch(it) } },
    )
}

@Composable
private fun PokemonScreen(
    modifier: Modifier = Modifier,
    stateFlow: StateFlow<PokemonViewModel.State>,
    onNavigateBack: () -> Unit,
    onIntent: (PokemonViewModel.Intent) -> Unit,
) {

    val state by stateFlow.collectAsState()

    PokedexTheme {

        AppScaffold(
            modifier = modifier,
            title = {
                Row {

                    Text(
                        text = state.structure.title,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = state.structure.id,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(end = 24.dp)
                    )
                }
            },
            onNavigateBack = onNavigateBack,
            containerColor = state.structure.background,
            onContainerColor = state.structure.onBackground
        ) { innerModifier ->

            when (val innerState = state) {

                is PokemonViewModel.State.Error -> {
                    FailureScreen(
                        modifier = innerModifier,
                        onContainerColor = state.structure.onBackground,
                        onClick = remember { { onIntent(PokemonViewModel.Intent.Retry) } },
                    )
                }

                is PokemonViewModel.State.Loading -> {
                    LoadingScreen(
                        modifier = innerModifier,
                        onContainerColor = state.structure.onBackground
                    )
                }

                is PokemonViewModel.State.Success -> {
                    SuccessScreen(
                        modifier = innerModifier,
                        vo = innerState.data,
                        onNavigateBack = onNavigateBack,
                    )
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SuccessScreen(
    modifier: Modifier,
    vo: PokemonDetailDataView,
    onNavigateBack: () -> Unit,
) {

    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = spacedBy(16.dp),
    ) {

        TypeTagsWidget(types = vo.types)

        WidgetTemplate {
            AsyncImage(
                modifier = Modifier.fillMaxWidth().height(200.dp),
                model = vo.image.url,
                contentDescription = vo.image.contentDescription
            )
        }

        WidgetTemplate {
            Text(
                text = vo.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        WidgetTemplate(
            title = stringResource(Res.string.detail_about),
            items = vo.about,
            onItem = {
                FieldValueComponent(
                    field = stringResource(it.field),
                    value = it.value
                )
            }
        )

        WidgetTemplate(
            title = stringResource(Res.string.detail_breeding),
            items = vo.breeding,
            onItem = {
                FieldValueComponent(
                    field = stringResource(it.field),
                    value = it.value
                )
            }
        )

        WidgetTemplate(
            title = stringResource(Res.string.detail_stats),
            items = vo.stats,
            onItem = {
                ChartComponent(
                    field = stringResource(it.field),
                    value = it.value,
                    progress = it.progress,
                    color = it.color
                )
            }
        )

        WidgetTemplate(
            title = stringResource(Res.string.detail_moves),
            columns = 2,
            items = vo.moves,
            onItem = {
                GridFieldValueComponent(
                    key = it.id,
                    value = it.name
                )
            }
        )

        Spacer(Modifier.padding(bottom = 32.dp))
    }

}

@Composable
internal fun WidgetTemplate(
    modifier: Modifier = Modifier,
    title: String? = null,
    content: @Composable () -> Unit,
) {

    CardComponent(modifier = modifier) {

        title?.let {
            CardTitleComponent(title = title)
        }

        content()
    }
}

@Composable
internal fun <T : Any> WidgetTemplate(
    modifier: Modifier = Modifier,
    title: String? = null,
    columns: Int = 1,
    items: List<T>,
    onItem: @Composable (item: T) -> Unit,
) {

    WidgetTemplate(
        modifier = modifier,
        title = title
    ) {

        FlowRow(
            maxItemsInEachRow = columns,
            horizontalArrangement = Arrangement.Absolute.spacedBy(8.dp),
            verticalArrangement = Arrangement.Absolute.spacedBy(8.dp),
        ) {

            items.forEach {
                key(it) {
                    Box(Modifier.weight(1f / columns)) {
                        onItem(it)
                    }
                }
            }
        }
    }
}

@Composable
internal fun GridFieldValueComponent(
    modifier: Modifier = Modifier,
    key: String,
    value: String,
) {

    Row(modifier = modifier) {

        Text(
            modifier = Modifier.padding(end = 8.dp),
            text = key,
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            modifier = Modifier.weight(1f),
            text = value,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
internal fun FieldValueComponent(
    modifier: Modifier = Modifier,
    field: String,
    value: String,
) {

    Row(verticalAlignment = CenterVertically) {

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
}

@Composable
internal fun ChartComponent(
    modifier: Modifier = Modifier,
    field: String,
    value: String,
    progress: Float,
    color: Color,
) {

    Row(
        modifier = modifier,
        verticalAlignment = CenterVertically
    ) {

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

@Preview
@Composable
private fun PokemonScreenPreview(
    @PreviewParameter(PokemonStatePreviewProvider::class) state: PokemonViewModel.State,
) {
    PokemonScreen(
        stateFlow = remember(state) { MutableStateFlow(state) },
        onNavigateBack = {},
        onIntent = {},
    )
}

private class PokemonStatePreviewProvider : PreviewParameterProvider<PokemonViewModel.State> {

    val structure1 = PokemonDetailStructureView(PokemonSummaryParam(1, "Bulbasaur", "", "Grass"))
    val structure2 = PokemonDetailStructureView(PokemonSummaryParam(25, "Pikachu", "", "Electric"))

    override val values: Sequence<PokemonViewModel.State> = sequenceOf(
        PokemonViewModel.State.Loading(structure1),
        PokemonViewModel.State.Error(structure1),
        PokemonViewModel.State.Success(structure1, data = previewDetailVo()),
        PokemonViewModel.State.Loading(structure2),
        PokemonViewModel.State.Error(structure2),
        PokemonViewModel.State.Success(structure2, data = previewDetailVo()),
    )
}

private fun previewDetailVo(): PokemonDetailDataView =
    PokemonDetailDataView(
        types = listOf(
            PokemonDetailDataView.Type("Grass"),
            PokemonDetailDataView.Type("Poison"),
        ),
        image = PokemonDetailDataView.ArtWork(
            url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            contentDescription = "Bulbasaur",
        ),
        description = "A strange seed was planted on its back at birth.",
        about = listOf(
            PokemonDetailDataView.FieldValue(Res.string.detail_about_species, "Seed"),
            PokemonDetailDataView.FieldValue(Res.string.detail_about_category, "Seed Pokémon"),
            PokemonDetailDataView.FieldValue(Res.string.detail_about_height, "70 cm"),
            PokemonDetailDataView.FieldValue(Res.string.detail_about_weight, "6.9 kg"),
            PokemonDetailDataView.FieldValue(Res.string.detail_about_abilities, "Overgrow"),
        ),
        breeding = listOf(
            PokemonDetailDataView.FieldValue(Res.string.detail_breeding_male, "87.5%"),
            PokemonDetailDataView.FieldValue(Res.string.detail_breeding_female, "12.5%"),
            PokemonDetailDataView.FieldValue(Res.string.detail_breeding_egg_groups, "Monster, Grass"),
        ),
        stats = listOf(
            PokemonDetailDataView.Stat(Res.string.detail_stats_hp, 45),
            PokemonDetailDataView.Stat(Res.string.detail_stats_atk, 49),
            PokemonDetailDataView.Stat(Res.string.detail_stats_def, 49),
            PokemonDetailDataView.Stat(Res.string.detail_stats_sp_atk, 65),
            PokemonDetailDataView.Stat(Res.string.detail_stats_sp_def, 65),
            PokemonDetailDataView.Stat(Res.string.detail_stats_speed, 45),
            PokemonDetailDataView.Stat(Res.string.detail_stats_total, 318, 600),
        ),
        moves = listOf(
            PokemonDetailDataView.Move("#001", "Tackle"),
            PokemonDetailDataView.Move("#002", "Growl"),
        ),
    )

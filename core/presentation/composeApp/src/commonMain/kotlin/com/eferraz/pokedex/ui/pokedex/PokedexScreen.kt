package com.eferraz.pokedex.ui.pokedex

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.eferraz.pokedex.components.templates.AppScaffold
import com.eferraz.pokedex.components.templates.FailureScreen
import com.eferraz.pokedex.components.templates.LoadingScreen
import com.eferraz.pokedex.components.templates.PokedexTheme
import com.eferraz.pokedex.entity.detail.Type
import com.eferraz.pokedex.entity.summary.PokemonSummary
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import pokedex.features.composeapp.generated.resources.Res
import pokedex.features.composeapp.generated.resources.pokedex_title

@Composable
internal fun PokedexScreen(
    modifier: Modifier = Modifier,
    onClick: (PokemonSummary) -> Unit,
) {

    val vm: PokedexViewModel = koinViewModel()

    PokedexScreen(
        modifier = modifier,
        stateFlow = vm.state,
        onClick = onClick,
        onIntent = vm::dispatch,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PokedexScreen(
    modifier: Modifier = Modifier,
    stateFlow: StateFlow<PokedexViewModel.UiState>,
    onClick: (PokemonSummary) -> Unit,
    onIntent: (PokedexViewModel.Intent) -> Unit,
) {

    val state by stateFlow.collectAsState()

    PokedexTheme {

        AppScaffold(
            modifier = modifier,
            title = stringResource(Res.string.pokedex_title),
            containerColor = MaterialTheme.colorScheme.surface,
            onContainerColor = MaterialTheme.colorScheme.onSurface
        ) { innerModifier ->

            when (val innerState = state) {

                PokedexViewModel.UiState.Error -> {
                    FailureScreen(
                        modifier = innerModifier,
                        onContainerColor = MaterialTheme.colorScheme.onSurface,
                        onClick = { onIntent(PokedexViewModel.Intent.Retry) },
                    )
                }

                PokedexViewModel.UiState.Loading -> {
                    LoadingScreen(
                        modifier = innerModifier,
                        onContainerColor = MaterialTheme.colorScheme.onSurface
                    )
                }

                is PokedexViewModel.UiState.Success -> {
                    SuccessScreen(
                        modifier = innerModifier,
                        summariesById = innerState.summaries,
                        onClick = onClick,
                        onItemVisible = { onIntent(PokedexViewModel.Intent.ItemVisible(it)) },
                    )
                }
            }
        }
    }
}

@Composable
private fun SuccessScreen(
    modifier: Modifier,
    summariesById: Map<Long, PokemonSummary>,
    onClick: (PokemonSummary) -> Unit,
    onItemVisible: (PokemonSummary) -> Unit,
) {

    val rowsInListOrder = remember(summariesById) {
        summariesById.values.toList()
    }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(bottom = 36.dp),
    ) {

        items(
            items = rowsInListOrder,
            key = { it.id }
        ) { row ->

            val uiModel = remember(row) { row.toPokedexListItemUi() }

            LaunchedEffect(row.id) {
                onItemVisible(row)
            }

            PokemonItemListView(
                model = uiModel,
                onClick = { onClick(row) },
            )
        }
    }
}

@Preview
@Composable
private fun PokedexScreenPreview(
    @PreviewParameter(PokedexStatePreviewProvider::class) state: PokedexViewModel.UiState,
) {
    PokedexScreen(
        stateFlow = remember(state) { MutableStateFlow(state) },
        onClick = {},
        onIntent = {},
    )
}

private class PokedexStatePreviewProvider : PreviewParameterProvider<PokedexViewModel.UiState> {

    override val values: Sequence<PokedexViewModel.UiState> = sequenceOf(
        PokedexViewModel.UiState.Loading,
        PokedexViewModel.UiState.Error,
        PokedexViewModel.UiState.Success(summaries = pokemonList().associateBy { it.id }),
    )

    private fun pokemonList(): List<PokemonSummary> = listOf(
        PokemonSummary(
            id = 1,
            name = "bulbasaur",
            type1 = Type(12, "grass"),
            artwork = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            type2 = Type(4, "poison"),
        ),
        PokemonSummary(
            id = 4,
            name = "charmander",
            type1 = Type(10, "fire"),
            artwork = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png",
            type2 = null,
        ),
        PokemonSummary(
            id = 25,
            name = "pikachu",
            type1 = Type(13, "electric"),
            artwork = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
            type2 = null,
        ),
    )
}

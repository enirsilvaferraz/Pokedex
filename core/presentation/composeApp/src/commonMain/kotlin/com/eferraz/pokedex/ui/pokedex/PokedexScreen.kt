package com.eferraz.pokedex.ui.pokedex

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.eferraz.pokedex.components.PokemonItemListView
import com.eferraz.pokedex.entity.BasePokemon
import com.eferraz.pokedex.entity.detail.Type
import com.eferraz.pokedex.entity.summary.PokemonSummary
import com.eferraz.pokedex.helpers.AppScaffold
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun PokedexScreen(
    modifier: Modifier = Modifier,
    onClick: (BasePokemon) -> Unit,
) {

    val vm: PokedexViewModel = koinViewModel()

    PokedexScreen(
        modifier = modifier,
        stateFlow = vm.state,
        onClick = onClick,
        onIntent = remember { { vm.dispatch(it) } },
    )
}

@Composable
private fun PokedexScreen(
    modifier: Modifier = Modifier,
    stateFlow: StateFlow<PokedexViewModel.UiState>,
    onClick: (BasePokemon) -> Unit,
    onIntent: (PokedexViewModel.Intent) -> Unit,
) {

    val state by stateFlow.collectAsState()

    AppScaffold(
        modifier = modifier,
        title = "Pokedex",
    ) { innerModifier ->

        when (val s = state) {

            PokedexViewModel.UiState.Error -> {
                FailureScreen(
                    modifier = innerModifier,
                    onClick = remember { { onIntent(PokedexViewModel.Intent.Retry) } },
                )
            }

            PokedexViewModel.UiState.Loading -> {
                LoadingScreen(
                    modifier = innerModifier,
                )
            }

            is PokedexViewModel.UiState.Success -> {
                SuccessScreen(
                    modifier = innerModifier,
                    summariesById = s.summaries,
                    onClick = onClick,
                    onItemVisible = remember { { onIntent(PokedexViewModel.Intent.ItemVisible(it)) } },
                )
            }
        }
    }
}

@Composable
private fun SuccessScreen(
    modifier: Modifier,
    summariesById: Map<Long, BasePokemon>,
    onClick: (BasePokemon) -> Unit,
    onItemVisible: (BasePokemon) -> Unit,
) {

    val rowsInListOrder = remember(summariesById) {
        summariesById.values.toList()
    }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(24.dp),
    ) {

        items(
            items = rowsInListOrder,
            key = { it.id }
        ) { row ->

            val uiModel = remember(row) { row.toPokedexListItemUi() }

            val latestRequest by rememberUpdatedState(onItemVisible)
            LaunchedEffect(row.id) {
                latestRequest(row)
            }

            PokemonItemListView(
                model = uiModel,
                onClick = remember(row) { { onClick(row) } },
            )
        }
    }
}

@Composable
private fun LoadingScreen(
    modifier: Modifier,
) {

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun FailureScreen(
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Text(
            text = "Não foi possível carregar a lista.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
        )

        TextButton(onClick = onClick) {
            Text("Tentar novamente")
        }
    }
}

@Preview
@Composable
private fun NewPokedexScreenPreview(
    @PreviewParameter(NewPokedexStatePreviewProvider::class) state: PokedexViewModel.UiState,
) {
    val stateFlow = remember(state) { MutableStateFlow(state) }
    PokedexScreen(
        stateFlow = stateFlow,
        onClick = {},
        onIntent = {},
    )
}

@Preview
@Composable
private fun NewPokedexSuccessScreenPreview() {
    val items = newPokedexPreviewPokemonList()
    val success = PokedexViewModel.UiState.Success(
        summaries = items.associateBy { it.id },
    )
    val stateFlow = remember { MutableStateFlow<PokedexViewModel.UiState>(success) }
    PokedexScreen(
        stateFlow = stateFlow,
        onClick = {},
        onIntent = {},
    )
}

private fun newPokedexPreviewPokemonList(): List<PokemonSummary> = listOf(
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

private class NewPokedexStatePreviewProvider : PreviewParameterProvider<PokedexViewModel.UiState> {
    override val values: Sequence<PokedexViewModel.UiState> = run {
        val previewItems = newPokedexPreviewPokemonList()
        sequenceOf(
            PokedexViewModel.UiState.Loading,
            PokedexViewModel.UiState.Error,
            PokedexViewModel.UiState.Success(summaries = previewItems.associateBy { it.id },),
        )
    }
}

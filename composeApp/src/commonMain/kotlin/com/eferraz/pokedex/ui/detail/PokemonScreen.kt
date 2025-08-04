package com.eferraz.pokedex.ui.detail

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import com.eferraz.pokedex.helpers.PokedexTheme
import com.eferraz.pokedex.helpers.edgeToEdgePadding
import com.eferraz.pokedex.ui.detail.vos.PokemonDetailVo
import com.eferraz.pokedex.ui.detail.widgets.ChartWidget
import com.eferraz.pokedex.ui.detail.widgets.FieldValueWidget
import com.eferraz.pokedex.ui.detail.widgets.GridFieldValueWidget
import com.eferraz.pokedex.ui.detail.widgets.ImageWidget
import com.eferraz.pokedex.ui.detail.widgets.ParagraphWidget
import com.eferraz.pokedex.ui.detail.widgets.TypeTagsWidget
import com.eferraz.pokedex.ui.preview.pokemon
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import pokedex.composeapp.generated.resources.Res
import pokedex.composeapp.generated.resources.detail_screen_about
import pokedex.composeapp.generated.resources.detail_screen_back_button
import pokedex.composeapp.generated.resources.detail_screen_breeding
import pokedex.composeapp.generated.resources.detail_screen_moves
import pokedex.composeapp.generated.resources.detail_screen_stats

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
private fun PokemonScreen(
    modifier: Modifier,
    state: PokemonViewModel.State,
    onNavigateBack: () -> Unit,
) {

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
    vo: PokemonDetailVo,
    onNavigateBack: () -> Unit,
) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                header = vo.header,
                onNavigateBack = onNavigateBack,
                scrollBehavior = scrollBehavior
            )
        },
        containerColor = vo.background.color
    ) {

        LazyColumn(
            modifier = Modifier.edgeToEdgePadding(it, LocalLayoutDirection.current),
            verticalArrangement = spacedBy(16.dp),
            contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 32.dp)
        ) {

            item { TypeTagsWidget(types = vo.types) }

            item { ImageWidget(image = vo.image) }

            item { ParagraphWidget(description = vo.description) }

            item { FieldValueWidget(title = stringResource(Res.string.detail_screen_about), items = vo.about.items) }

            item { FieldValueWidget(title = stringResource(Res.string.detail_screen_breeding), items = vo.breeding.items) }

            item { ChartWidget(title = stringResource(Res.string.detail_screen_stats), items = vo.stats.items) }

            item {
                GridFieldValueWidget(
                    title = stringResource(Res.string.detail_screen_moves),
                    items = vo.moves.items
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    header: PokemonDetailVo.Header,
    onNavigateBack: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    MediumTopAppBar(
        title = {
            Row {
                Text(header.name, color = Color.White, modifier = Modifier.weight(1f))
                Text(header.id, color = Color.White, modifier = Modifier.padding(end = 24.dp))
            }
        },
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(Res.string.detail_screen_back_button),
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

@Preview
@Composable
private fun SuccessScreenPreview() {
    PokemonScreen(
        modifier = Modifier,
        state = PokemonViewModel.Success(pokemon),
        onNavigateBack = {}
    )
}

@Preview
@Composable
private fun LoadingScreenPreview() {
    PokemonScreen(
        modifier = Modifier,
        state = PokemonViewModel.Loading,
        onNavigateBack = {}
    )
}

@Preview
@Composable
private fun FailureScreenPreview() {
    PokemonScreen(
        modifier = Modifier,
        state = PokemonViewModel.Error,
        onNavigateBack = {}
    )
}
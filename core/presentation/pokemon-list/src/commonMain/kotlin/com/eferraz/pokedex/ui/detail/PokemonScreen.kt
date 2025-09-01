package com.eferraz.pokedex.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.eferraz.pokedex.helpers.PokedexTheme
import com.eferraz.pokedex.helpers.edgeToEdgePadding
import com.eferraz.pokedex.ui.detail.components.CardComponent
import com.eferraz.pokedex.ui.detail.components.CardTitleComponent
import com.eferraz.pokedex.ui.detail.vos.PokemonDetailVo
import com.eferraz.pokedex.ui.detail.widgets.TypeTagsWidget
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import pokedex.pokemon_list.generated.resources.Res
import pokedex.pokemon_list.generated.resources.detail_about
import pokedex.pokemon_list.generated.resources.detail_back_button
import pokedex.pokemon_list.generated.resources.detail_breeding
import pokedex.pokemon_list.generated.resources.detail_moves
import pokedex.pokemon_list.generated.resources.detail_stats

@Composable
public fun PokemonRoute(
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
                name = vo.header.name,
                id = vo.header.id,
                onNavigateBack = onNavigateBack,
                scrollBehavior = scrollBehavior
            )
        },
        containerColor = vo.background.color
    ) { padding ->

        LazyColumn(
            modifier = Modifier.edgeToEdgePadding(padding, LocalLayoutDirection.current),
            verticalArrangement = spacedBy(16.dp),
            contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 32.dp)
        ) {

            item {
                TypeTagsWidget(types = vo.types)
            }

            item {
                WidgetTemplate {
                    AsyncImage(
                        modifier = Modifier.fillMaxWidth().height(200.dp),
                        model = vo.image.url,
                        contentDescription = vo.image.contentDescription
                    )
                }
            }

            item {
                WidgetTemplate {
                    Text(
                        text = vo.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            item {
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
            }

            item {
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
            }

            item {
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
            }

            item {
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
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    name: String,
    id: String,
    onNavigateBack: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    MediumTopAppBar(
        title = {
            Row {
                Text(name, color = Color.White, modifier = Modifier.weight(1f))
                Text(id, color = Color.White, modifier = Modifier.padding(end = 24.dp))
            }
        },
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(Res.string.detail_back_button),
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
){

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

//@Preview
//@Composable
//private fun SuccessScreenPreview() {
//    PokemonScreen(
//        modifier = Modifier,
//        state = PokemonViewModel.Success(pokemon),
//        onNavigateBack = {}
//    )
//}

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
package com.eferraz.pokedex.components.templates

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import pokedex.features.composeapp.generated.resources.Res
import pokedex.features.composeapp.generated.resources.detail_back_button

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AppScaffold(
    modifier: Modifier = Modifier,
    title: String,
    onNavigateBack: (() -> Unit)? = null,
    containerColor: Color,
    onContainerColor: Color,
    content: @Composable (modifier: Modifier) -> Unit,
) {

    AppScaffold(
        modifier = modifier,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(start = 8.dp),
            )
        },
        onNavigateBack = onNavigateBack,
        containerColor = containerColor,
        onContainerColor = onContainerColor,
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AppScaffold(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    onNavigateBack: (() -> Unit)? = null,
    containerColor: Color,
    onContainerColor: Color,
    content: @Composable (modifier: Modifier) -> Unit,
) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {

            MediumTopAppBar(
                title = title,
                navigationIcon = {
                    if (onNavigateBack != null) {
                        IconButton(onClick = onNavigateBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(Res.string.detail_back_button),
                                tint = onContainerColor
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent,
                    navigationIconContentColor = onContainerColor,
                    titleContentColor = onContainerColor
                ),
                scrollBehavior = scrollBehavior
            )
        },
        containerColor = containerColor
    ) { padding ->

        content(
            Modifier.edgeToEdgePadding(padding, LocalLayoutDirection.current)
                .padding(start = 24.dp, end = 24.dp)
                .fillMaxWidth()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        )
    }
}

private fun Modifier.edgeToEdgePadding(innerPadding: PaddingValues, current: LayoutDirection): Modifier = padding(
    top = innerPadding.calculateTopPadding(),
    start = innerPadding.calculateStartPadding(current),
    end = innerPadding.calculateEndPadding(current),
)

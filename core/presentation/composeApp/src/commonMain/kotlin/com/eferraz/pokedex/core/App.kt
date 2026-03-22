package com.eferraz.pokedex.core

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.eferraz.pokedex.core.params.PokemonSummaryParam
import com.eferraz.pokedex.ui.detail.PokemonScreen
import com.eferraz.pokedex.ui.pokedex.PokedexScreen

@Composable
public fun InternalApp() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = PokemonListRouting) {

        composable<PokemonListRouting> {
            PokedexScreen {
                navController.navigate(
                    PokemonDetailRouting(
                        id = it.id,
                        name = it.name,
                        artwork = it.artwork,
                        type1 = it.type1?.name,
                    ),
                )
            }
        }

        composable<PokemonDetailRouting> { backStackEntry ->
            val route = backStackEntry.toRoute<PokemonDetailRouting>()
            PokemonScreen(
                ref = PokemonSummaryParam(
                    id = route.id,
                    name = route.name,
                    artwork = route.artwork,
                    type1 = route.type1,
                ),
            ) {
                navController.popBackStack()
            }
        }
    }
}


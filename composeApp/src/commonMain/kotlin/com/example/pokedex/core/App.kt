package com.example.pokedex.core

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.pokedex.PokemonDetailRoute
import com.example.pokedex.di.AppDI
import com.example.pokedex.list.PokedexRoute
import org.koin.compose.KoinMultiplatformApplication
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
public fun App() {

    @OptIn(KoinExperimentalAPI::class)
    KoinMultiplatformApplication(config = AppDI.koinConfiguration) {

        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = AppRoutes.PokemonListScreen) {

            composable<AppRoutes.PokemonListScreen> {
                PokedexRoute {
                    navController.navigate(AppRoutes.PokemonDetailScreen(it))
                }
            }

            composable<AppRoutes.PokemonDetailScreen> {
                PokemonDetailRoute((it.toRoute() as AppRoutes.PokemonDetailScreen).id)
            }
        }
    }
}


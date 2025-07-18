package com.eferraz.pokedex.core

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.eferraz.pokedex.di.AppDI
import com.eferraz.pokedex.ui.PokedexRoute
import com.eferraz.pokedex.ui.detail.PokemonRoute
import org.koin.compose.KoinMultiplatformApplication
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
public fun App() {

    @OptIn(KoinExperimentalAPI::class)
    KoinMultiplatformApplication(config = AppDI.koinConfiguration) {

        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = PokemonListRouting) {

            composable<PokemonListRouting> {
                PokedexRoute {
                    navController.navigate(PokemonDetailRouting(it.id))
                }
            }

            composable<PokemonDetailRouting> {
                PokemonRoute(id = (it.toRoute() as PokemonDetailRouting).id) {
                    navController.popBackStack()
                }
            }
        }
    }
}


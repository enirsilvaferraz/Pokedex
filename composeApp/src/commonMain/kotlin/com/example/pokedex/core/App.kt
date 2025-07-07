package com.example.pokedex.core

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.pokedex.di.AppDI
import com.example.pokedex.ui.PokedexRoute
import com.example.pokedex.ui.PokemonRoute
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


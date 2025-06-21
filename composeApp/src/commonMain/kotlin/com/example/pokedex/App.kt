package com.example.pokedex

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.pokedex.di.AppDI
import com.example.pokedex.list.PokedexRoute
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.koin.compose.KoinMultiplatformApplication
import org.koin.core.annotation.KoinExperimentalAPI

private object AppRoutes {

    @Serializable
    object Pokedex

    @Serializable
    data class Pokemon(@SerialName("id") val id: Int)
}

@Composable
fun App() {

    @OptIn(KoinExperimentalAPI::class)
    KoinMultiplatformApplication(config = AppDI.koinConfiguration) {

        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = AppRoutes.Pokedex) {

            composable<AppRoutes.Pokedex> {
                PokedexRoute {
                    navController.navigate(AppRoutes.Pokemon(it))
                }
            }

            composable<AppRoutes.Pokemon> {
                PokemonDetailRoute((it.toRoute() as AppRoutes.Pokemon).id)
            }
        }
    }
}


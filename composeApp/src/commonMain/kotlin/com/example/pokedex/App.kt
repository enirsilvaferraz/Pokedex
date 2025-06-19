package com.example.pokedex

import androidx.compose.runtime.Composable
import com.example.pokedex.di.AppDI
import org.koin.compose.KoinMultiplatformApplication
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun App() {

    @OptIn(KoinExperimentalAPI::class)
    KoinMultiplatformApplication(config = AppDI.koinConfiguration) {

        PokedexRoute {

        }
    }


//    PokedexRoute()
//    PokemonDetailRoute(1)
}


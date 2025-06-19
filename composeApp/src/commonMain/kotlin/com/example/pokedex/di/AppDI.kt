package com.example.pokedex.di

import com.example.pokedex.PokemonViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinConfiguration
import org.koin.dsl.module

internal object AppDI {

    private val composeAppModule = module {
        viewModelOf(::PokemonViewModel)
    }

    val koinConfiguration = KoinConfiguration {
        modules(composeAppModule)
    }
}
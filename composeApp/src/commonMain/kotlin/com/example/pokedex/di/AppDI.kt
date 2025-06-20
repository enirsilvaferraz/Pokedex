package com.example.pokedex.di

import com.example.pokedex.list.PokedexViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinConfiguration
import org.koin.dsl.module

internal object AppDI {

    private val composeAppModule = module {
        viewModelOf(::PokedexViewModel)
    }

    val koinConfiguration = KoinConfiguration {
        modules(composeAppModule)
    }
}
package com.example.pokedex.di

import com.example.pokedex.list.PokedexViewModel
import com.example.pokedex.network.PokedexDataSourceApi
import com.example.pokedex.network.configurations.ClientConfig
import com.example.pokedex.network.configurations.KtorClientConfig
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinConfiguration
import org.koin.dsl.bind
import org.koin.dsl.module

internal object AppDI {

    private val composeAppModule = module {
        viewModelOf(::PokedexViewModel)
        singleOf(::PokedexDataSourceApi)
        singleOf(::KtorClientConfig) bind ClientConfig::class
    }

    val koinConfiguration = KoinConfiguration {
        modules(composeAppModule)
    }
}
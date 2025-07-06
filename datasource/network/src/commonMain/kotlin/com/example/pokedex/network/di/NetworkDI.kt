package com.example.pokedex.network.di

import com.example.pokedex.network.core.ClientConfig
import com.example.pokedex.network.core.KtorClientConfig
import com.example.pokedex.network.datasources.PokemonApi
import com.example.repositories.datasources.PokemonDataSource
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

public object NetworkDI {

    public operator fun invoke(): Module = module {

        // Core
        singleOf(::KtorClientConfig) bind ClientConfig::class

        // Data Sources
        factoryOf(::PokemonApi) bind PokemonDataSource.Network::class
    }
}
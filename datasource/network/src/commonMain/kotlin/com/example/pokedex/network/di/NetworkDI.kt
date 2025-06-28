package com.example.pokedex.network.di

import com.example.pokedex.network.adapters.PokemonDataSourceApi
import com.example.pokedex.network.core.ClientConfig
import com.example.pokedex.network.core.KtorClientConfig
import com.example.pokedex.network.datasources.PokedexApi
import com.example.pokedex.network.datasources.PokedexApiImpl
import com.example.pokedex.network.datasources.PokemonApi
import com.example.pokedex.network.datasources.PokemonApiImpl
import com.example.repositories.datasources.ReadableDataSource
import com.example.repositories.di.AppQualifiers
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

public object NetworkDI {

    public operator fun invoke(): Module = module {

        // Core
        singleOf(::KtorClientConfig) bind ClientConfig::class

        // APIs
        factoryOf(::PokedexApiImpl) bind PokedexApi::class
        factoryOf(::PokemonApiImpl) bind PokemonApi::class

        // Data Sources
        factoryOf(::PokemonDataSourceApi, { qualifier = AppQualifiers.Pokemon.network() }) bind ReadableDataSource::class
    }
}
package com.example.pokedex.network.di

import com.example.pokedex.network.core.ClientConfig
import com.example.pokedex.network.core.KtorClientConfig
import com.example.pokedex.network.datasources.PokedexApi
import com.example.pokedex.network.datasources.PokemonApi
import com.example.repositories.datasources.PokedexDataSource
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

        // APIs
//        factoryOf(::PokemonApiImpl) bind PokemonApi::class

        // Data Sources
//        factoryOf(::PokemonDataSourceApi, { qualifier = AppQualifiers.Pokemon.network() }) bind ReadableDataSource::class
        factoryOf(::PokedexApi) bind PokedexDataSource.Network::class
        factoryOf(::PokemonApi) bind PokemonDataSource.Network::class
    }
}
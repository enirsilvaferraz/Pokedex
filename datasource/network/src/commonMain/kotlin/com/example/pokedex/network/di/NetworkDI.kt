package com.example.pokedex.network.di

import com.example.pokedex.network.adapters.PokedexAdapter
import com.example.pokedex.network.adapters.PokedexAdapterImpl
import com.example.pokedex.network.configurations.ClientConfig
import com.example.pokedex.network.configurations.KtorClientConfig
import com.example.pokedex.network.datasources.PokedexDataSourceApi
import com.example.pokedex.network.datasources.PokemonDataSourceApi
import com.example.pokedex.network.datasources.PokemonSpeciesDataSourceApi
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object NetworkDI {

    operator fun invoke() = module {

        // Configuration
        singleOf(::KtorClientConfig) bind ClientConfig::class

        // DSs
        factoryOf(::PokedexDataSourceApi) bind PokedexDataSourceApi::class
        factoryOf(::PokemonSpeciesDataSourceApi) bind PokemonSpeciesDataSourceApi::class
        factoryOf(::PokemonDataSourceApi) bind PokemonDataSourceApi::class

        // Adapters
        factoryOf(::PokedexAdapterImpl) bind PokedexAdapter::class
    }
}
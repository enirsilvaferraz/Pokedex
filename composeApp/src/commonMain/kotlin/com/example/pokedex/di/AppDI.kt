package com.example.pokedex.di

import com.example.pokedex.list.PokedexRepository
import com.example.pokedex.list.PokedexRepositoryImpl
import com.example.pokedex.list.PokedexViewModel
import com.example.pokedex.network.di.NetworkDI
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinConfiguration
import org.koin.dsl.bind
import org.koin.dsl.module

internal object AppDI {

    private operator fun invoke() = module {

        // ViewModels
        viewModelOf(::PokedexViewModel)

        // Repositories
        factoryOf(::PokedexRepositoryImpl) bind PokedexRepository::class
    }

    val koinConfiguration = KoinConfiguration {
        modules(
            AppDI(),
            NetworkDI()
        )
    }
}
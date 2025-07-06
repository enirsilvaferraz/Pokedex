package com.example.pokedex.di

import com.example.pokedex.database.di.DatabaseDI
import com.example.pokedex.list.PokedexViewModel
import com.example.pokedex.network.di.NetworkDI
import com.example.pokedex.usecases.di.UseCasesDI
import com.example.repositories.di.RepositoryDI
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinConfiguration
import org.koin.dsl.module

internal object AppDI {

    private operator fun invoke() = module {

        // ViewModels
        viewModelOf(::PokedexViewModel)
    }

    val koinConfiguration = KoinConfiguration {
        modules(
            AppDI(),
            UseCasesDI(),
            RepositoryDI(),
            NetworkDI(),
            DatabaseDI()
        )
    }
}
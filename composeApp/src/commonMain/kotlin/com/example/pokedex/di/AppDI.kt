package com.example.pokedex.di

import com.example.pokedex.database.di.DatabaseDI
import com.example.pokedex.ui.PokedexViewModel
import com.example.pokedex.network.di.NetworkDI
import com.example.pokedex.ui.PokemonViewModel
import com.example.pokedex.usecases.di.UseCasesDI
import com.example.repositories.di.RepositoryDI
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinConfiguration
import org.koin.dsl.module

internal object AppDI {

    private operator fun invoke() = module {

        // ViewModels
        viewModelOf(::PokedexViewModel)
        viewModelOf(::PokemonViewModel)
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
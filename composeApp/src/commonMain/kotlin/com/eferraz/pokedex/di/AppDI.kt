package com.eferraz.pokedex.di

import com.eferraz.pokedex.database.di.DatabaseDI
import com.eferraz.pokedex.ui.PokedexViewModel
import com.eferraz.pokedex.network.di.NetworkDI
import com.eferraz.pokedex.ui.detail.PokemonViewModel
import com.eferraz.pokedex.usecases.di.UseCasesDI
import com.eferraz.repositories.di.RepositoryDI
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
package com.eferraz.pokedex.di

import com.eferraz.pokedex.database.di.DatabaseDI
import com.eferraz.pokedex.network.di.NetworkDI
import com.eferraz.pokedex.usecases.di.UseCasesDI
import com.eferraz.repositories.di.RepositoryModule
import org.koin.dsl.KoinConfiguration
import org.koin.ksp.generated.module

internal object AppDI {

    operator fun invoke() = KoinConfiguration {
        modules(
            UseCasesDI(),
            NetworkDI(),
            DatabaseDI(),
            RepositoryModule().module,
            ComposeModule().module,
        )
    }
}
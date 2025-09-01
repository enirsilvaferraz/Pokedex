package com.eferraz.pokedex.di

import com.eferraz.pokedex.database.di.DatabaseModule
import com.eferraz.pokedex.entity.di.EntityModule
import com.eferraz.pokedex.network.di.NetworkModule
import com.eferraz.pokedex.repositories.di.RepositoryModule
import com.eferraz.pokedex.usecases.di.UseCaseModule
import org.koin.dsl.KoinConfiguration
import org.koin.ksp.generated.module

internal object AppDI {

    operator fun invoke() = KoinConfiguration {
        modules(
            DatabaseModule().module,
            NetworkModule().module,
            EntityModule().module,
            UseCaseModule().module,
            RepositoryModule().module,
            ComposeAppModule().module,
        )
    }
}
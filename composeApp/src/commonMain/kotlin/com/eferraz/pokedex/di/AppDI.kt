package com.eferraz.pokedex.di

import com.eferraz.pokedex.database.di.DatabaseDI
import com.eferraz.pokedex.entity.di.EntityModule
import com.eferraz.pokedex.network.di.NetworkModule
import com.eferraz.pokedex.usecases.di.UseCaseModule
import com.eferraz.pokedex.repositories.di.RepositoryModule
import org.koin.dsl.KoinConfiguration
import org.koin.ksp.generated.module

internal object AppDI {

    operator fun invoke() = KoinConfiguration {
        modules(
            DatabaseDI(),
            NetworkModule().module,
            EntityModule().module,
            UseCaseModule().module,
            RepositoryModule().module,
            ComposeModule().module,
        )
    }
}
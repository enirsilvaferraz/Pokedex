package com.eferraz.pokedex.di

import com.eferraz.pokedex.database.di.DatabaseModule
import com.eferraz.pokedex.entity.di.EntityModule
import com.eferraz.pokedex.network.di.NetworkModule
import com.eferraz.pokedex.repositories.di.RepositoryModule
import com.eferraz.pokedex.usecases.di.UseCaseModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.KoinApplication

@KoinApplication(
    modules = [
        DatabaseModule::class,
        NetworkModule::class,
        EntityModule::class,
        UseCaseModule::class,
        RepositoryModule::class,
        ComposeModule::class
    ]
)
@ComponentScan("com.eferraz.investments")
internal class MyKoinApp
package com.eferraz.pokedex.repositories.di

import com.eferraz.pokedex.database.di.DatabaseModule
import com.eferraz.pokedex.network.di.NetworkModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(
    includes = [
        NetworkModule::class,
        DatabaseModule::class
    ]
)
@ComponentScan("com.eferraz.pokedex.repositories")
public class RepositoryModule

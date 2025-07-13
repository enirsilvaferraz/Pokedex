package com.eferraz.repositories.di

import com.eferraz.repositories.PokemonListRepository
import com.eferraz.repositories.PokemonListRepositoryImpl
import com.eferraz.repositories.PokemonRepository
import com.eferraz.repositories.PokemonRepositoryImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

public object RepositoryDI {

    public operator fun invoke(): Module = module {

        // Repositories
        factoryOf(::PokemonListRepositoryImpl) bind PokemonListRepository::class
        factoryOf(::PokemonRepositoryImpl) bind PokemonRepository::class
    }
}
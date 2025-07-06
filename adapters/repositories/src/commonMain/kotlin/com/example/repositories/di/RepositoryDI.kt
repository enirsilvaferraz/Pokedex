package com.example.repositories.di

import com.example.repositories.PokemonRepository
import com.example.repositories.PokemonRepositoryImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

public object RepositoryDI {

    public operator fun invoke(): Module = module {

        // Repositories
        factoryOf(::PokemonRepositoryImpl) bind PokemonRepository::class
    }
}
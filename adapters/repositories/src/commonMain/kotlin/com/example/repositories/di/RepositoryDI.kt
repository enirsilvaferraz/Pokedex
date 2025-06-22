package com.example.repositories.di

import com.example.repositories.PokedexRepository
import com.example.repositories.PokedexRepositoryImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

object RepositoryDI {

    operator fun invoke() = module {

        // Repositories
        factoryOf(::PokedexRepositoryImpl) bind PokedexRepository::class
    }
}
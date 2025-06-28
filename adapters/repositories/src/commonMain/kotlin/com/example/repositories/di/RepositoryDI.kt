package com.example.repositories.di

import com.example.repositories.PokemonRepository
import com.example.repositories.PokemonRepositoryImpl
import org.koin.dsl.module

object RepositoryDI {

    operator fun invoke() = module {

        // Repositories
        factory<PokemonRepository> {
            PokemonRepositoryImpl(
                listOf(
                    get(AppQualifiers.Pokemon.database()),
                    get(AppQualifiers.Pokemon.network())
                )
            )
        }
    }
}
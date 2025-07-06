package com.example.pokedex.usecases.di

import com.example.pokedex.usecases.GetPokemonUseCase
import com.example.pokedex.usecases.GetPokemonUseCaseImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

object UseCasesDI {

    operator fun invoke() = module {
        factoryOf(::GetPokemonUseCaseImpl) bind GetPokemonUseCase::class
    }
}
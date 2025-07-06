package com.example.pokedex.usecases.di

import com.example.pokedex.usecases.GetPokemonUseCase
import com.example.pokedex.usecases.GetPokemonUseCaseImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

public object UseCasesDI {

    public operator fun invoke(): Module = module {
        factoryOf(::GetPokemonUseCaseImpl) bind GetPokemonUseCase::class
    }
}
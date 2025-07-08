package com.example.pokedex.usecases.di

import com.example.pokedex.usecases.GetPokemonListUseCaseImpl
import com.example.pokedex.usecases.GetPokemonUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

public object UseCasesDI {

    public operator fun invoke(): Module = module {
        factoryOf(::GetPokemonListUseCaseImpl) bind GetPokemonUseCase::class
    }
}
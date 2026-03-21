package com.eferraz.pokedex.usecases

import com.eferraz.pokedex.entity.summary.PokemonSummary
import com.eferraz.pokedex.usecases.core.AppUseCase
import kotlinx.coroutines.CoroutineDispatcher

public abstract class GetPokemonDetailsUseCase(
    dispatcher: CoroutineDispatcher,
) : AppUseCase<Long, PokemonSummary>(dispatcher)

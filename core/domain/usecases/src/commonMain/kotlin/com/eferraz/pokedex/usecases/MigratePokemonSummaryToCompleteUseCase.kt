package com.eferraz.pokedex.usecases

import com.eferraz.pokedex.entity.summary.PokemonSummary
import com.eferraz.pokedex.usecases.core.AppUseCase
import kotlinx.coroutines.CoroutineDispatcher

public abstract class MigratePokemonSummaryToCompleteUseCase(
    dispatcher: CoroutineDispatcher,
) : AppUseCase<PokemonSummary, Unit>(dispatcher)

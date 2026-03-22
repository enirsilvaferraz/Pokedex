package com.eferraz.pokedex.usecases

import com.eferraz.pokedex.entity.summary.PokemonSummary
import kotlinx.coroutines.flow.Flow

public fun interface ObservePokemonListUseCase {
    public operator fun invoke(): Flow<List<PokemonSummary>>
}

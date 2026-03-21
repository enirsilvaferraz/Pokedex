package com.eferraz.pokedex.usecases

import com.eferraz.pokedex.entity.BasePokemon
import kotlinx.coroutines.flow.Flow

public fun interface NewObservePokemonListUseCase {
    public operator fun invoke(): Flow<List<BasePokemon>>
}

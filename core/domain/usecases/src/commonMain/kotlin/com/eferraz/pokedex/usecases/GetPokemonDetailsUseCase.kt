package com.eferraz.pokedex.usecases

import com.eferraz.pokedex.entity.detail.PokemonDetailed
import com.eferraz.pokedex.usecases.core.AppUseCase
import kotlinx.coroutines.CoroutineDispatcher

public abstract class GetPokemonDetailsUseCase(
    dispatcher: CoroutineDispatcher,
) : AppUseCase<GetPokemonDetailsUseCase.Params, PokemonDetailed>(dispatcher) {

    public data class Params(val id: Long)
}

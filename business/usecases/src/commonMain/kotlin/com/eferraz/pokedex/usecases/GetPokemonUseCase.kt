package com.eferraz.pokedex.usecases

import app.cash.paging.PagingData
import com.eferraz.pokedex.entity.PokemonLightVO
import kotlinx.coroutines.flow.Flow

public interface GetPokemonUseCase {
    public operator fun invoke(): Flow<PagingData<PokemonLightVO>>
}
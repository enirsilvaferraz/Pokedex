package com.example.pokedex.usecases

import app.cash.paging.PagingData
import com.example.pokedex.entity.PokemonVO
import kotlinx.coroutines.flow.Flow

public interface GetPokemonUseCase {
    public operator fun invoke(): Flow<PagingData<PokemonVO>>
}
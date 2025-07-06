package com.example.pokedex.usecases

import app.cash.paging.PagingData
import com.example.pokedex.entity.PokemonVO
import kotlinx.coroutines.flow.Flow

interface GetPokemonUseCase {
    operator fun invoke(): Flow<PagingData<PokemonVO>>
}
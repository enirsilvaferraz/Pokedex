package com.example.pokedex.list

import com.example.pokedex.PokemonVO
import kotlinx.coroutines.flow.Flow

internal interface GetPokedexUseCase {
    operator fun invoke(): Flow<PokemonVO>
}
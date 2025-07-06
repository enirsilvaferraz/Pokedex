package com.example.pokedex.usecases

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import com.example.repositories.PokemonRepository

internal class GetPokemonUseCaseImpl(
    private val repository: PokemonRepository,
) : GetPokemonUseCase {

    override operator fun invoke() = Pager(
        config = PagingConfig(pageSize = 1, prefetchDistance = 10, initialLoadSize = 1),
        pagingSourceFactory = { repository },
    ).flow
}
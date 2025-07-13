package com.eferraz.pokedex.usecases

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import com.eferraz.repositories.PokemonListRepository

internal class GetPokemonListUseCaseImpl(
    private val repository: PokemonListRepository,
) : GetPokemonUseCase {

    override operator fun invoke() = Pager(
        config = PagingConfig(pageSize = 1, prefetchDistance = 10, initialLoadSize = 1),
        pagingSourceFactory = { repository },
    ).flow
}
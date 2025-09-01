package com.eferraz.pokedex.usecases

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import com.eferraz.pokedex.usecases.repositories.PokemonListRepository
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Provided

@Factory
internal class GetPokemonListUseCaseImpl(
    @Provided private val repository: PokemonListRepository,
) : GetPokemonUseCase {

    override operator fun invoke() = Pager(
        config = PagingConfig(pageSize = 1, prefetchDistance = 10, initialLoadSize = 1),
        pagingSourceFactory = { repository },
    ).flow
}
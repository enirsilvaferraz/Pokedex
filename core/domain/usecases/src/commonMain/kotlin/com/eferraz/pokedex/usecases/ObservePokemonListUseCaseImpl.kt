package com.eferraz.pokedex.usecases

import com.eferraz.pokedex.entity.BasePokemon
import com.eferraz.pokedex.usecases.repositories.PokemonSummaryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory

@Factory
internal class ObservePokemonListUseCaseImpl(
    private val dispatcher: CoroutineDispatcher,
    private val repository: PokemonSummaryRepository,
) : ObservePokemonListUseCase {

    override fun invoke(): Flow<List<BasePokemon>> =
        repository.getAll().flowOn(dispatcher)
}

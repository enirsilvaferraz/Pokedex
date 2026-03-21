package com.eferraz.pokedex.usecases

import com.eferraz.pokedex.entity.BasePokemon
import com.eferraz.pokedex.usecases.repositories.PokemonRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory

@Factory
internal class NewObservePokemonListUseCaseImpl(
    private val dispatcher: CoroutineDispatcher,
    private val repository: PokemonRepository,
) : NewObservePokemonListUseCase {

    override fun invoke(): Flow<List<BasePokemon>> =
        repository.getAll().flowOn(dispatcher)
}

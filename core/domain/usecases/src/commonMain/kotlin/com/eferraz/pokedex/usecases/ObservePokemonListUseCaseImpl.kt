package com.eferraz.pokedex.usecases

import com.eferraz.pokedex.entity.BasePokemon
import com.eferraz.pokedex.usecases.repositories.PokemonRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
internal class ObservePokemonListUseCaseImpl(
    private val repository: PokemonRepository,
) : ObservePokemonListUseCase {

    override fun invoke(): Flow<List<BasePokemon>> = repository.getAll()
}

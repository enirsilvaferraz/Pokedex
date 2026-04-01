package com.eferraz.pokedex.usecases

import com.eferraz.pokedex.entity.detail.PokemonDetailed
import com.eferraz.pokedex.usecases.repositories.PokemonDetailedRepository
import com.eferraz.pokedex.usecases.repositories.PokemonSpeciesRepository
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.annotation.Factory

@Factory
internal class GetPokemonDetailsUseCaseImpl(
    dispatcher: CoroutineDispatcher,
    private val detailedRepository: PokemonDetailedRepository,
    private val speciesRepository: PokemonSpeciesRepository,
) : GetPokemonDetailsUseCase(dispatcher) {

    override suspend fun execute(param: Params): PokemonDetailed {

        val detailed = requireNotNull(detailedRepository.get(param.id)) {
            "Pokemon not found: id=${param.id}"
        }

        if (detailed.species != null) return detailed

        val species = speciesRepository.get(param.id)
            .also { speciesRepository.upsert(it) }

        return detailed.copy(species = species)
    }
}

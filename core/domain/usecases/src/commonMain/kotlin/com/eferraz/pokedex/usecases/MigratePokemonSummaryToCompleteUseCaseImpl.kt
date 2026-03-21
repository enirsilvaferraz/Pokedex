package com.eferraz.pokedex.usecases

import com.eferraz.pokedex.entity.summary.PokemonSummary
import com.eferraz.pokedex.usecases.repositories.PokemonDetailedRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit
import org.koin.core.annotation.Factory

@Factory
internal class MigratePokemonSummaryToCompleteUseCaseImpl(
    dispatcher: CoroutineDispatcher,
    private val repository: PokemonDetailedRepository,
    private val mutex: Mutex,
    private val semaphore: Semaphore,
) : MigratePokemonSummaryToCompleteUseCase(dispatcher) {

    private val inFlight = mutableSetOf<Long>()

    override suspend fun execute(param: PokemonSummary) {

        // Verifica se o id já foi adicionado ao semaforo para processamento
        val shouldProcess = mutex.withLock { inFlight.add(param.id) }

        if (shouldProcess) try {

            // Adiciona ao semaforo para processamento
            semaphore.withPermit {
                repository.fetch(param.id)
            }

        } finally {

            // Sinaliza como processado. Só é chamado após a conclusão do bloco try
            mutex.withLock { inFlight.remove(param.id) }
        }
    }
}

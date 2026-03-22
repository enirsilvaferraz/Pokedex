package com.eferraz.pokedex.usecases

import com.eferraz.pokedex.usecases.repositories.PokemonDetailedRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertTrue

class UpdatePokemonTypeUseCaseImplTest {

    @AfterTest
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `GIVEN summary WHEN invoke THEN fetches detailed pokemon by id`() = runTest {
        // GIVEN
        val dispatcher = StandardTestDispatcher(testScheduler)
        val repository = mockk<PokemonDetailedRepository>()
        val detailed = testPokemonDetailed(id = 5L)
        coEvery { repository.fetch(id = 5L) } returns detailed
        val useCase = UpdatePokemonTypeUseCaseImpl(
            dispatcher = dispatcher,
            repository = repository,
            mutex = Mutex(),
            semaphore = Semaphore(permits = 3),
        )
        val summary = testPokemonSummary(id = 5L)

        // WHEN
        val result = useCase(summary)

        // THEN
        assertTrue(result.isSuccess)
        coVerify(exactly = 1) { repository.fetch(id = 5L) }
    }
}

package com.eferraz.pokedex.usecases

import com.eferraz.pokedex.usecases.repositories.PokemonSummaryRepository
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class ObservePokemonListUseCaseImplTest {

    @AfterTest
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `GIVEN repository emits list WHEN flow is collected THEN same list is received on dispatcher`() =
        runTest {
            // GIVEN
            val dispatcher = StandardTestDispatcher(testScheduler)
            val fromRepo = listOf(testPokemonSummary(id = 2L))
            val repository = mockk<PokemonSummaryRepository>()
            every { repository.getAll() } returns flowOf(fromRepo)
            val useCase = ObservePokemonListUseCaseImpl(
                dispatcher = dispatcher,
                repository = repository,
            )

            // WHEN
            val emitted = useCase.invoke().first()

            // THEN
            assertContentEquals(fromRepo, emitted)
        }
}

package com.eferraz.pokedex.usecases

import com.eferraz.pokedex.usecases.repositories.PokemonDetailedRepository
import com.eferraz.pokedex.usecases.repositories.PokemonSpeciesRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetPokemonDetailsUseCaseImplTest {

    @AfterTest
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `GIVEN detailed already has species WHEN invoke THEN returns it without calling species repository`() =
        runTest {
            // GIVEN
            val dispatcher = StandardTestDispatcher(testScheduler)
            val species = testSpecies(id = 9L)
            val detailed = testPokemonDetailed(id = 9L, species = species)
            val detailedRepo = mockk<PokemonDetailedRepository>()
            val speciesRepo = mockk<PokemonSpeciesRepository>()
            coEvery { detailedRepo.get(id = 9L) } returns detailed
            val useCase = GetPokemonDetailsUseCaseImpl(
                dispatcher = dispatcher,
                detailedRepository = detailedRepo,
                speciesRepository = speciesRepo,
            )

            // WHEN
            val result = useCase(GetPokemonDetailsUseCase.Params(id = 9L))

            // THEN
            assertTrue(result.isSuccess)
            assertEquals(detailed, result.getOrNull())
            coVerify(exactly = 0) { speciesRepo.get(any()) }
            coVerify(exactly = 0) { speciesRepo.upsert(any()) }
        }

    @Test
    fun `GIVEN detailed without species WHEN invoke THEN fetches species upserts and merges`() =
        runTest {
            // GIVEN
            val dispatcher = StandardTestDispatcher(testScheduler)
            val fetchedSpecies = testSpecies(id = 3L)
            val detailed = testPokemonDetailed(id = 3L, species = null)
            val detailedRepo = mockk<PokemonDetailedRepository>()
            val speciesRepo = mockk<PokemonSpeciesRepository>()
            coEvery { detailedRepo.get(id = 3L) } returns detailed
            coEvery { speciesRepo.get(id = 3L) } returns fetchedSpecies
            coEvery { speciesRepo.upsert(any()) } returns Unit
            val useCase = GetPokemonDetailsUseCaseImpl(
                dispatcher = dispatcher,
                detailedRepository = detailedRepo,
                speciesRepository = speciesRepo,
            )

            // WHEN
            val result = useCase(GetPokemonDetailsUseCase.Params(id = 3L))

            // THEN
            assertTrue(result.isSuccess)
            assertEquals(detailed.copy(species = fetchedSpecies), result.getOrNull())
            coVerify(exactly = 1) { speciesRepo.get(id = 3L) }
            coVerify(exactly = 1) { speciesRepo.upsert(fetchedSpecies) }
        }

    @Test
    fun `GIVEN detailed repository returns null WHEN invoke THEN failure`() = runTest {
        // GIVEN
        val dispatcher = StandardTestDispatcher(testScheduler)
        val detailedRepo = mockk<PokemonDetailedRepository>()
        val speciesRepo = mockk<PokemonSpeciesRepository>()
        coEvery { detailedRepo.get(id = 1L) } returns null
        val useCase = GetPokemonDetailsUseCaseImpl(
            dispatcher = dispatcher,
            detailedRepository = detailedRepo,
            speciesRepository = speciesRepo,
        )

        // WHEN
        val result = useCase(GetPokemonDetailsUseCase.Params(id = 1L))

        // THEN
        assertTrue(result.isFailure)
        coVerify(exactly = 0) { speciesRepo.get(any()) }
    }
}

package com.eferraz.pokedex.repositories

import com.eferraz.pokedex.database.datasources.PokemonDetailedDataSourceDatabase
import com.eferraz.pokedex.network.datasources.PokemonDetailedDataSourceApi
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class PokemonDetailedRepositoryImplTest {

    @AfterTest
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `GIVEN detailed pokemon is cached WHEN get is called THEN returns database value without calling API`() = runTest {

        // GIVEN
        val cached = sampleDetailed()
        val api = mockk<PokemonDetailedDataSourceApi>()
        val db = mockk<PokemonDetailedDataSourceDatabase>()

        coEvery { db.get(id = 1L) } returns cached

        val repo = PokemonDetailedRepositoryImpl(api = api, database = db)

        // WHEN
        val result = repo.get(id = 1L)

        // THEN
        assertSame(cached, result)
        coVerify(exactly = 0) { api.get(any()) }
    }

    @Test
    fun `GIVEN detailed pokemon is not cached WHEN get is called THEN fetches from API persists and returns entity`() = runTest {

        // GIVEN
        val fromApi = sampleDetailed(id = 4L)
        val api = mockk<PokemonDetailedDataSourceApi>()
        val db = mockk<PokemonDetailedDataSourceDatabase>()

        coEvery { db.get(id = 4L) } returns null
        coEvery { api.get(id = 4L) } returns fromApi
        coEvery { db.upsert(any()) } returns Unit

        val repo = PokemonDetailedRepositoryImpl(api = api, database = db)

        // WHEN
        val result = repo.get(id = 4L)

        // THEN
        assertEquals(fromApi, result)
        coVerify(exactly = 1) { api.get(id = 4L) }
        coVerify(exactly = 1) { db.upsert(fromApi) }
    }

    @Test
    fun `GIVEN a PokemonDetailed entity WHEN upsert is called THEN delegates to database`() = runTest {

        // GIVEN
        val api = mockk<PokemonDetailedDataSourceApi>()
        val db = mockk<PokemonDetailedDataSourceDatabase>()

        coEvery { db.upsert(any()) } returns Unit

        val repo = PokemonDetailedRepositoryImpl(api = api, database = db)
        val entity = sampleDetailed()

        // WHEN
        repo.upsert(entity = entity)

        // THEN
        coVerify(exactly = 1) { db.upsert(entity) }
        coVerify(exactly = 0) { api.get(any()) }
    }
}

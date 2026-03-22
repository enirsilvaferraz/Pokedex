package com.eferraz.pokedex.repositories

import com.eferraz.pokedex.database.datasources.PokemonSummaryDataSourceDatabase
import com.eferraz.pokedex.network.datasources.PokemonSummaryDataSourceApi
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class PokemonSummaryRepositoryImplTest {

    @AfterTest
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `GIVEN database already returns summaries WHEN getAll is collected THEN emits them and does not call the paged API`() = runTest {

        // GIVEN
        val fromDb = listOf(sampleSummary())
        val api = mockk<PokemonSummaryDataSourceApi>()
        val db = mockk<PokemonSummaryDataSourceDatabase>()

        every { db.get(limit = 151, offset = 0) } returns flowOf(fromDb)

        val repo = PokemonSummaryRepositoryImpl(api = api, database = db)

        // WHEN
        val emitted = repo.getAll().first()

        // THEN
        assertContentEquals(fromDb, emitted)
        coVerify(exactly = 0) { api.get(any(), any()) }
    }

    @Test
    fun `GIVEN database emits empty list WHEN getAll is collected THEN fetches Kanto page from API and upserts into database`() = runTest {

        // GIVEN
        val fromApi = listOf(sampleSummary(id = 25L, name = "pikachu"))
        val api = mockk<PokemonSummaryDataSourceApi>()
        val db = mockk<PokemonSummaryDataSourceDatabase>()

        every { db.get(limit = 151, offset = 0) } returns flowOf(emptyList())
        coEvery { api.get(limit = 151, offset = 0) } returns fromApi
        coEvery { db.upsert(*anyVararg()) } returns Unit

        val repo = PokemonSummaryRepositoryImpl(api = api, database = db)

        // WHEN
        repo.getAll().first()

        // THEN
        coVerify(exactly = 1) { api.get(limit = 151, offset = 0) }
        coVerify(exactly = 1) { db.upsert(*fromApi.toTypedArray()) }
    }
}

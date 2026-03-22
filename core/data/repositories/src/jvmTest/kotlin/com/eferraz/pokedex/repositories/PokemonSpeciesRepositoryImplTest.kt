package com.eferraz.pokedex.repositories

import com.eferraz.pokedex.database.datasources.PokemonSpeciesDataSourceDatabase
import com.eferraz.pokedex.network.datasources.PokemonSpeciesDataSourceApi
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

class PokemonSpeciesRepositoryImplTest {

    @AfterTest
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `GIVEN API returns species WHEN get is called THEN returns API value for requested id`() = runTest {

        // GIVEN
        val expected = sampleSpecies(id = 3L)
        val api = mockk<PokemonSpeciesDataSourceApi>()
        val db = mockk<PokemonSpeciesDataSourceDatabase>(relaxUnitFun = true)

        coEvery { api.get(id = 3L) } returns expected

        val repo = PokemonSpeciesRepositoryImpl(api = api, database = db)

        // WHEN
        val result = repo.get(id = 3L)

        // THEN
        assertEquals(expected, result)
        coVerify(exactly = 1) { api.get(id = 3L) }
        coVerify(exactly = 0) { db.upsert(any()) }
    }

    @Test
    fun `GIVEN a Species entity WHEN upsert is called THEN delegates to database`() = runTest {

        // GIVEN
        val api = mockk<PokemonSpeciesDataSourceApi>(relaxUnitFun = true)
        val db = mockk<PokemonSpeciesDataSourceDatabase>()

        coEvery { db.upsert(any()) } returns Unit

        val repo = PokemonSpeciesRepositoryImpl(api = api, database = db)
        val entity = sampleSpecies()

        // WHEN
        repo.upsert(entity = entity)

        // THEN
        coVerify(exactly = 1) { db.upsert(entity) }
    }
}

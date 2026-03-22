package com.eferraz.pokedex.entity.summary

import com.eferraz.pokedex.entity.detail.Type
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PokemonSummaryTest {

    @Test
    fun `GIVEN type1 is null WHEN isPlaceholder THEN true`() {
        // GIVEN
        val summary = PokemonSummary(id = 1L, name = "a", type1 = null)

        // WHEN / THEN
        assertTrue(summary.isPlaceholder())
    }

    @Test
    fun `GIVEN type1 is set WHEN isPlaceholder THEN false`() {
        // GIVEN
        val summary = PokemonSummary(id = 1L, name = "a", type1 = Type(id = 1L, name = "grass"))

        // WHEN / THEN
        assertFalse(summary.isPlaceholder())
    }

    @Test
    fun `GIVEN one or two types WHEN types THEN returns non null types in order`() {
        // GIVEN
        val t1 = Type(id = 1L, name = "grass")
        val t2 = Type(id = 2L, name = "poison")
        val one = PokemonSummary(id = 1L, name = "a", type1 = t1, type2 = null)
        val two = PokemonSummary(id = 1L, name = "a", type1 = t1, type2 = t2)

        // WHEN / THEN
        assertContentEquals(listOf(t1), one.types())
        assertContentEquals(listOf(t1, t2), two.types())
    }
}

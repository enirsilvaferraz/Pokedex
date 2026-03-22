package com.eferraz.pokedex.entity.detail

import com.eferraz.pokedex.entity.summary.PokemonSummary
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class PokemonDetailedTest {

    @Test
    fun `GIVEN detailed with two types WHEN types THEN matches listOfNotNull order`() {
        // GIVEN
        val t1 = Type(id = 1L, name = "grass")
        val t2 = Type(id = 2L, name = "poison")
        val detailed = testDetailed(type1 = t1, type2 = t2)

        // WHEN / THEN
        assertContentEquals(listOf(t1, t2), detailed.types())
    }

    @Test
    fun `GIVEN detailed WHEN toSummary THEN maps id name types and image as artwork`() {
        // GIVEN
        val t1 = Type(id = 3L, name = "fire")
        val detailed = testDetailed(
            id = 4L,
            name = "charmander",
            image = "https://example.com/4.png",
            type1 = t1,
            type2 = null,
        )

        // WHEN
        val summary: PokemonSummary = detailed.toSummary()

        // THEN
        assertEquals(detailed.id, summary.id)
        assertEquals(detailed.name, summary.name)
        assertEquals(t1, summary.type1)
        assertEquals(null, summary.type2)
        assertEquals(detailed.image, summary.artwork)
    }
}

private fun testDetailed(
    id: Long = 1L,
    name: String = "bulbasaur",
    image: String = "https://example.com/1.png",
    type1: Type = Type(id = 1L, name = "grass"),
    type2: Type? = null,
): PokemonDetailed = PokemonDetailed(
    id = id,
    name = name,
    image = image,
    type1 = type1,
    type2 = type2,
    species = null,
    about = About(id = id, height = 0.7f, weight = 6.9f, abilities = emptyList()),
    stats = Stats(
        id = id,
        hp = 45,
        attack = 49,
        defense = 49,
        specialAttack = 65,
        specialDefense = 65,
        speed = 45,
    ),
    moves = emptyList(),
)

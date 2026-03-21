package com.eferraz.pokedex.entity.detail

import com.eferraz.pokedex.entity.BasePokemon
import com.eferraz.pokedex.entity.summary.PokemonSummary

public data class PokemonDetailed(
    override val id: Long,
    override val name: String,
    public val image: String,
    public val type1: Type,
    public val type2: Type?,
    val about: About,
    val breeding: Breeding,
    val stats: Stats,
    val moves: List<Move>,
) : BasePokemon {

    public val artwork: String get() = image

    public fun types(): List<Type> = listOfNotNull(type1, type2)

    public fun toSummary(): PokemonSummary =
        PokemonSummary(id = id, name = name, type1 = type1, artwork = image, type2 = type2)
}
